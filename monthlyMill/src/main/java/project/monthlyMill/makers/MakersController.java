package project.monthlyMill.makers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/makers")
public class MakersController {
	
	@GetMapping("/main")
	public String mainPage() {
		return "/makers/makerMain";
	}
}
