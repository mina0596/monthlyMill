package project.monthlyMill.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Member;
import project.monthlyMill.dto.MemberAgreementInfo;
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
	
	@PostMapping("/join_method")
	public String memberJoinCate(@RequestParam(name = "joinType", required = false) int memberCate
								,HttpSession session) {
		session.setAttribute("JCate", memberCate);
		log.info("joinType 잘 데려오는지 확인:{}", memberCate);
		return "redirect:/join/join_agreement";
	}
	
	//약관동의
	@GetMapping("/join_agreement")
	public String getMemberJoinAgreement() {
		return "/memberJoin/join_agreement";
	}
	
	//약관동의 후 처리과정
	@PostMapping("/join_agreement")
	public String memberJoinAgreement(@RequestParam (name = "newsAgree", required = false) String newsAgree
									, HttpSession session) {
		log.info("newsAgree 체크했는지의 여부 :{}", newsAgree);
		
		session.setAttribute("JAgree", newsAgree);
		
		return "redirect:/join/join_basic";
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
								 , @RequestParam(name = "inputSex", required = false) String memberGender
								 , @RequestParam(name = "inputPhone", required = false) String memberPhone
								 , @RequestParam(name = "inputBday", required = false) String memberBday
								 , @RequestParam(name = "inputAge", required = false) int memberAge
								 , @RequestParam(name = "inputAddr", required = false) String memberAddr
								 , @RequestParam(name = "inputPostCode", required = false) String memberPostalCode
								 , @RequestParam(name = "inputBank", required = false) String refundAccountBank
								 , @RequestParam(name = "inputAccountOwner", required = false) String refundName
								 , @RequestParam(name = "inputAccountNumber", required = false) String refundAccount
								 , HttpSession session) {
		Member inputInfo = new Member();
		inputInfo.setMemberId(memberId);
		inputInfo.setMemberPw(memberPw);
		inputInfo.setMemberName(memberName);
		inputInfo.setMemberGender(memberGender);
		inputInfo.setMemberPhone(memberPhone);
		inputInfo.setMemberBday(memberBday);
		inputInfo.setMemberAge(memberAge);
		log.info("member 기본정보란 확인:{}", inputInfo);
		session.setAttribute("JInfo", inputInfo);
		return "redirect:/join/join_additory";
	}
	
	//추가정보 입력창 (전부 선택입력란)
	@GetMapping("/join_additory")
	public String memberJoinAdditory(HttpSession session, Model model) {
		log.info("session에 잘 저장되는지 확인:{}", session.getAttribute("joinInfo"));
		
		//해시태그 제일 하위분류에서는 이름이 겹치는게 없다는 가정하에 만들었음
		List<Hashtag> tagNames = joinService.getTagNames();
		List<String> midClassList = new ArrayList<String>();
		for(int i=0; i<tagNames.size(); i++) {
			midClassList.add(tagNames.get(i).getHashtagMidClass());
		}
		midClassList = midClassList.stream().distinct().collect(Collectors.toList());
		model.addAttribute("tagNames", tagNames);
		model.addAttribute("midClassList", midClassList);
		return "/memberJoin/join_additory";
	}
	
	@PostMapping("/join_additory")
	public String memberJoinAdd() {
		return "redirect:/join/join_finish";
	}
	
	@GetMapping("/join_finish")
	public String memberJoinFinish() {
		return "/memberJoin/join_finish";
	}
	

}
