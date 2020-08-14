package CSCI5308.GroupFormationTool.Courses;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import CSCI5308.GroupFormationTool.AccessControl.User;

@Controller
public class CourseAdminController {
	private Logger logger = LoggerFactory.getLogger(CourseAdminController.class);
	private static final String ID = "id";
	private static final String TITLE = "title";
	private static final String INSTRUCTOR = "instructor";

	@GetMapping("/admin/course")
	public String course(Model model) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		List<Course> allCourses = courseDB.loadAllCourses();
		logger.info("Loading all the courses from the Database");
		model.addAttribute("courses", allCourses);
		logger.info("Adding the attribute named courses in the model to use for view");
		return "admin/course";
	}

	@GetMapping("/admin/assigninstructor")
	public String assignInstructor(Model model, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		Course course = new Course();
		course.loadCourseByID(courseDB, courseID);
		logger.info("Getting the course using the course ID : {}", courseID);
		model.addAttribute("course", course);
		logger.info("Adding the course attribute to the view");
		ICourseUserRelationshipPersistence courseUserRelationshipDB = CourseConfig.instance()
				.getCourseUserRelationshipDB();
		List<User> allUsersNotCurrentlyInstructors = courseUserRelationshipDB
				.findAllUsersWithoutCourseRole(Role.INSTRUCTOR, courseID);
		logger.info("Assigning the role to a user as {}", INSTRUCTOR);
		model.addAttribute("users", allUsersNotCurrentlyInstructors);
		logger.info("adding the users attribute to the model");
		return "admin/assigninstructor";
	}

	@GetMapping("/admin/deletecourse")
	public ModelAndView deleteCourse(@RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		Course course = new Course();
		course.setId(courseID);
		course.delete(courseDB);
		logger.info("Setting the course ID : {}", courseID);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/course");
		logger.info("Redirecting to course admin page");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/createcourse", method = RequestMethod.POST)
	public ModelAndView createCourse(@RequestParam(name = TITLE) String title) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		Course course = new Course();
		course.setTitle(title);
		course.createCourse(courseDB);
		logger.info("Creating course with a particular title : {}", title);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/course");
		logger.info("Redirecting to course admin page");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/assigninstructor", method = RequestMethod.POST)
	public ModelAndView assignInstructorToCourse(@RequestParam(name = INSTRUCTOR) List<Integer> instructor,
			@RequestParam(name = ID) long courseID) {
		Course course = new Course();
		course.setId(courseID);
		Iterator<Integer> iter = instructor.iterator();
		ICourseUserRelationshipPersistence courseUserRelationshipDB = CourseConfig.instance()
				.getCourseUserRelationshipDB();
		logger.info("Assigning an Instructor for a course");
		while (iter.hasNext()) {
			User user = new User();
			user.setId(iter.next().longValue());
			courseUserRelationshipDB.enrollUser(course, user, Role.INSTRUCTOR);
			logger.info("Changing the Role to Instructor for a Particular course");
		}
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/course");
		logger.info("Redirecting to course admin page");
		return modelAndView;
	}

}