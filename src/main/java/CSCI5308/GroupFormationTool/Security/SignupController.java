package CSCI5308.GroupFormationTool.Security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import CSCI5308.GroupFormationTool.AccessControl.*;

@Controller
public class SignupController {
	private final String USERNAME = "username";
	private final String PASSWORD = "password";
	private final String PASSWORD_CONFIRMATION = "passwordConfirmation";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String EMAIL = "email";

	@GetMapping("/signup")
	public String displaySignup(Model model) {
		SecurityConfig.instance().getPasswordPolicy();
		model.addAttribute("policies", PasswordPolicy.getPolicies());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView processSignup(@RequestParam(name = USERNAME) String bannerID,
			@RequestParam(name = PASSWORD) String password,
			@RequestParam(name = PASSWORD_CONFIRMATION) String passwordConfirm,
			@RequestParam(name = FIRST_NAME) String firstName, @RequestParam(name = LAST_NAME) String lastName,
			@RequestParam(name = EMAIL) String email) {
		boolean success = false;
		if (User.isBannerIDValid(bannerID) && User.isEmailValid(email) && User.isFirstNameValid(firstName)
				&& User.isLastNameValid(lastName) && password.equals(passwordConfirm)
				&& User.isPasswordValid(password, SecurityConfig.instance().getPasswordPolicy())) {
			User u = new User();
			u.setBannerID(bannerID);
			u.setPassword(password);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setEmail(email);
			IUserPersistence userDB = AccessControlConfig.instance().getUserDB();
			IPasswordEncryption passwordEncryption = SecurityConfig.instance().getPasswordEncryption();
			if (u.createUser(userDB, passwordEncryption, null)) {
				IUserAuthHistoryRelationshipPersistence userAuthHistoryRelationshipDB = SecurityConfig.instance()
						.getUserAuthHistoryRelationship();
				success = u.addPasswordHistory(userAuthHistoryRelationshipDB);
			}

		}
		ModelAndView m;
		if (success) {
			m = new ModelAndView("login");
		} else {
			m = new ModelAndView("signup");
			m.addObject("errorMessage", "Invalid data, please check your values.");
			m.addObject("policies", PasswordPolicy.getPolicies());
		}

		return m;
	}
}