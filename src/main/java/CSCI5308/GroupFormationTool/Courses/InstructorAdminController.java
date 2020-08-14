package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InstructorAdminController {
	private Logger logger = LoggerFactory.getLogger(InstructorAdminController.class);
	private static final String ID = "id";
	private static final String FILE = "file";
	private static final String SUCCESSFUL = "successful";
	private static final String FAILURES = "failures";
	private static final String DISPLAY_RESULTS = "displayresults";

	@GetMapping("/course/instructoradmin")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		Course course = new Course();
		course.loadCourseByID(courseDB, courseID);
		logger.info("Loading course by its course ID : {}", courseID);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		logger.info("Adding course and displayresults to the model");
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			logger.info("checking if Current user us enrolled in the course");
			return "course/instructoradmin";
		} else {
			logger.info("Logging out of the portal");
			return "logout";
		}
	}

	@GetMapping("/course/instructoradminresults")
	public String instructorAdmin(Model model, @RequestParam(name = ID) long courseID,
			@RequestParam(name = SUCCESSFUL, required = false) List<String> successful,
			@RequestParam(name = FAILURES, required = false) List<String> failures,
			@RequestParam(name = DISPLAY_RESULTS) boolean displayResults) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		Course course = new Course();
		course.loadCourseByID(courseDB, courseID);
		logger.info("Loading course by its course ID : {}", courseID);
		model.addAttribute("course", course);
		model.addAttribute("displayresults", false);
		logger.info("Adding course and displayresults to the model");
		model.addAttribute(SUCCESSFUL, successful);
		model.addAttribute(FAILURES, failures);
		model.addAttribute(DISPLAY_RESULTS, displayResults);
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			logger.info("checking if Current user us enrolled in the course");
			return "course/instructoradmin";
		} else {
			logger.info("Logging out of the portal");
			return "logout";
		}
	}

	@GetMapping("/course/enrollta")
	public String enrollTA(Model model, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		Course course = new Course();
		course.loadCourseByID(courseDB, courseID);
		logger.info("Loading course by its course ID : {}", courseID);
		model.addAttribute("course", course);
		logger.info("Adding course to the model");
		if (course.isCurrentUserEnrolledAsRoleInCourse(Role.INSTRUCTOR)
				|| course.isCurrentUserEnrolledAsRoleInCourse(Role.TA)) {
			logger.info("checking if Current user us enrolled in the course");
			return "course/enrollta";
		} else {
			logger.info("Logging out of the portal");
			return "logout";
		}
	}

	@RequestMapping(value = "/course/uploadcsv", consumes = { "multipart/form-data" })
	public ModelAndView upload(@RequestParam(name = FILE) MultipartFile file, @RequestParam(name = ID) long courseID) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		Course course = new Course();
		course.loadCourseByID(courseDB, courseID);
		logger.info("Loading course by its course ID : {}", courseID);
		IStudentCSVParser parser = new StudentCSVParser(file);
		logger.info("Parsing the CSV");
		StudentCSVImport importer = new StudentCSVImport(parser, course);
		logger.info("Importing the CSV in the Database");
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/course/instructoradminresults?id=" + Long.toString(courseID));
		modelAndView.addObject("successful", importer.getSuccessResults());
		modelAndView.addObject("failures", importer.getFailureResults());
		modelAndView.addObject("displayresults", true);
		logger.info("Adding successful, failures and displayresults attributes to the model");
		return modelAndView;
	}
}
