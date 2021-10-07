package project.monthlyMill.main;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String main(HttpSession session, Model model) {
		if(session.getAttribute("SMNUM") != null) {
			model.addAttribute("loginCheck", session.getAttribute("SMNUM"));
		}
		return "/common/main";
	}
}
