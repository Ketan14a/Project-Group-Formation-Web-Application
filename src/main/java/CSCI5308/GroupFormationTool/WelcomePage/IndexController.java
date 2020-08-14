package CSCI5308.GroupFormationTool.WelcomePage;

import CSCI5308.GroupFormationTool.AccessControl.AccessControlConfig;
import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.IRolePersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import CSCI5308.GroupFormationTool.Courses.*;
import CSCI5308.GroupFormationTool.Security.SecurityConfig;

@Controller
public class IndexController {
	@GetMapping("/")
	public String greeting(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			User currentUser = CurrentUser.instance().getCurrentAuthenticatedUser();
			if (null == currentUser) {
				return "login";
			}
			IRolePersistence roleDB = AccessControlConfig.instance().getRoleDB();
			List<String> roles = roleDB.loadRolesByUserID(currentUser.getID());
			if (roles.contains(Role.INSTRUCTOR.toString())) {
				model.addAttribute("hasInstructorRole", true);
				model.addAttribute("id", currentUser.getID());
			} else {
				model.addAttribute("hasInstructorRole", false);
			}
			ICoursePersistence courseDB = CourseConfig.instance().getCourseDB();
			List<Course> allCourses = courseDB.loadAllCourses();
			model.addAttribute("courses", allCourses);

		}
		SecurityConfig.instance().getPasswordPolicy();
		return "index";
	}
}