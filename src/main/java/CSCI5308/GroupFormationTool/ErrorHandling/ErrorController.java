package CSCI5308.GroupFormationTool.ErrorHandling;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorController {
	@GetMapping("/error")
	public String error(Model model) {
		return "error";
	}

	@GetMapping("/dbDownError")
	public String redirctToErrorPage(Model model) {
		return "dbDownError";
	}

	@PostMapping("/dbDownError")
	public String redirctionToErrorPage(Model model) {
		return "dbDownError";
	}
}