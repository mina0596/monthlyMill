package project.monthlyMill.makers.myMatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 메이커스의 my매칭에 대한 정보들 controller

@Controller
@RequestMapping("/makers/myMatch")
public class MakersMatchController {
	
	@GetMapping("/state")
	public String getMyMatchState() {
		return "/makers/makerMymatch_state";
	}
	
	@GetMapping("/history")
	public String getMatchHistory() {
		return "/makers/makerMymatch_history";
	}
}
