package project.monthlyMill.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.dto.Member;
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
	
	//가입방법 (회원가입 첫화면)
	@GetMapping("/join_method")
	public String memberJoinMethod() {
		return "/memberJoin/join_method";
	}
	
	//약관동의
	@GetMapping("/join_agreement")
	public String memberJoinAgreement() {
		return "/memberJoin/join_agreement";
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
	
	//기본정보 입력창 (전부 필수입력란) 세션에 저장
	@PostMapping("/join_basic")
	public String getJoinBasicInfo(@RequestParam(name = "inputId", required = false) String memberId
								 , @RequestParam(name = "inputPw", required = false) String memberPw
								 , @RequestParam(name = "inputName", required = false) String memberName
								 , @RequestParam(name = "inputAge", required = false) int memberAge
								 , @RequestParam(name = "inputSex", required = false) String memberGender
								 , @RequestParam(name = "inputPhone", required = false) String memberPhone
								 , HttpSession session) {
		Member inputInfo = new Member();
		inputInfo.setMemberAge(memberAge);
		inputInfo.setMemberId(memberId);
		inputInfo.setMemberPw(memberPw);
		inputInfo.setMemberGender(memberGender);
		inputInfo.setMemberName(memberName);
		inputInfo.setMemberPhone(memberPhone);
		log.info("member 기본정보란 확인:{}", inputInfo);
		session.setAttribute("joinInfo", inputInfo);
		return "/memberJoin/join_additory";
	}
	
	//추가정보 입력창 (전부 선택입력란)
	@GetMapping("/join_additory")
	public String memberJoinAdditory(HttpSession session) {
		log.info("session에 잘 저장되는지 확인:{}", session.getAttribute("joinInfo"));
		return "/memberJoin/join_additory";
	}

}
