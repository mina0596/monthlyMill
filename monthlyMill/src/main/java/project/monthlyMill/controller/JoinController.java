package project.monthlyMill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/join")
public class JoinController {
	
	//회원가입
	@GetMapping("/join_basic")
	public String memberJoin() {
		return "/memberJoin/join_basic";
	}
	
	@PostMapping("/memberIdCheck")
	@ResponseBody
	public Boolean getMemberIdCheckResult(@RequestParam (value = "inputId") String inputId) {
		
		return true;
	}

}
