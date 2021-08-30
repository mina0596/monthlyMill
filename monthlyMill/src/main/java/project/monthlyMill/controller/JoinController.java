package project.monthlyMill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.service.JoinService;

@Controller
@RequestMapping("/join")
public class JoinController {
	
	private static final Logger log = LoggerFactory.getLogger(JoinController.class);
	
	private final JoinService joinService;
	
	@Autowired
	public JoinController(JoinService joinService) {
		this.joinService = joinService;
	}
	
	//기본정보 입력창 (전부 필수입력란)
	@GetMapping("/join_basic")
	public String memberJoinBasic() {
		return "/memberJoin/join_basic";
	}
	
	
	
	//아이디 중복 확인
	@PostMapping("/memberIdCheck")
	@ResponseBody
	public boolean getMemberIdCheckResult(@RequestParam (name = "inputId", required = false) String inputId) {
		log.info("입력된 아이디 확인: {}", inputId);
		boolean idCheckResult = joinService.getMemberInfoById(inputId);
		log.info("아이디 중복 확인: {}", idCheckResult);
		return idCheckResult;
	}
	
	//추가정보 입력창 (전부 선택입력란)
	@GetMapping("/join_additory")
	public String memberJoinAdditory() {
		return "/memberJoin/join_additory";
	}

}
