package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import CSCI5308.GroupFormationTool.ResponseSurvey.IResponseSurveyPersistence;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseSurvey;
import CSCI5308.GroupFormationTool.ResponseSurvey.ResponseSurveyConfig;

@Controller
public class CourseController {
	private static final String ID = "id";

	private Logger logger = LoggerFactory.getLogger(CourseController.class);

	@GetMapping("/course/course")
	public String course(Model model, @RequestParam(name = ID) long courseID,
			@RequestParam(name = "instructorID") Integer instructorID) {
		ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
		IResponseSurveyPersistence responseSurveyDB = ResponseSurveyConfig.instance().getResponseSurveyDB();
		Course course = new Course();
		courseDB.loadCourseByID(courseID, course);
		logger.info("Loading the Course by Course ID : {}", course);
		model.addAttribute("course", course);
		logger.info("Adding the course attribute to the model");
		ResponseSurvey checkSurvey = new ResponseSurvey();
		responseSurveyDB.loadSurveyByCourseID(courseID, checkSurvey);

		logger.info("Load Survey if the available for this particular course");
		model.addAttribute("surveyAvailable", checkSurvey.isSurveyPublished());
		List<Role> userRoles = course.getAllRolesForCurrentUserInCourse();
		if (null == userRoles) {
			model.addAttribute("instructor", false);
			model.addAttribute("ta", false);
			model.addAttribute("student", false);
			model.addAttribute("guest", true);

			logger.info("Setting a new user as guest");
		} else {
			model.addAttribute("instructorID", instructorID);
			model.addAttribute("instructor", userRoles.contains(Role.INSTRUCTOR));
			model.addAttribute("ta", userRoles.contains(Role.TA));
			model.addAttribute("student", userRoles.contains(Role.STUDENT));
			model.addAttribute("guest", userRoles.isEmpty());
			logger.info("Assigning role as INSTRUCTOR, TA and STUDENT");
		}
		return "course/course";
	}
}
