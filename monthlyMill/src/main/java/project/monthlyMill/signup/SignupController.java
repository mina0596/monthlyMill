package project.monthlyMill.signup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Member;
import project.monthlyMill.dto.MemberAgreementInfo;
import project.monthlyMill.dto.RefundAccount;

@Controller
@RequestMapping("/join")
public class SignupController {
	
	private static final Logger log = LoggerFactory.getLogger(SignupController.class);
	
	private final SignupService signupService;
	
	@Autowired
	public SignupController(SignupService signupService) {
		this.signupService = signupService;
	}
	
	// 1.가입방법 (회원가입 첫화면) - 고객, 메이커스 선택
	@GetMapping("/join_method")
	public String memberJoinMethod() {
		return "/memberJoin/join_method";
	}
	
	@PostMapping("/join_method")
	public String memberJoinCate(@RequestParam(name = "joinType", required = false) int memberCate
								,HttpSession session, RedirectAttributes redirectAttr) {
		session.setAttribute("memberCate", memberCate);
		log.info("joinType 잘 데려오는지 확인:{}", memberCate);
		return "redirect:/join/join_agreement";
	}
	
	// 2.약관동의
	@GetMapping("/join_agreement")
	public String getMemberJoinAgreement() {
		return "/memberJoin/join_agreement";
	}

	@PostMapping("/join_agreement")
	public String memberJoinAgreement(@RequestParam (name = "newsAgree", required = false) String newsAgree
									, HttpSession session) {
		log.info("newsAgree 체크했는지의 여부 :{}", newsAgree);
		
		session.setAttribute("JAgree", newsAgree);
		
		return "redirect:/join/join_basic";
	}
	
	// 3.기본정보 입력창 (전부 필수입력란)
	@GetMapping("/join_basic")
	public String memberJoinBasic() {
		return "/memberJoin/join_basic";
	}
	
	// 아이디 중복 확인
	@PostMapping("/memberIdCheck")
	@ResponseBody
	public boolean getMemberIdCheckResult(@RequestParam (name = "inputId", required = false) String inputId) {
		log.info("입력된 아이디 확인: {}", inputId);
		boolean idCheckResult = signupService.getMemberInfoById(inputId);
		log.info("아이디 중복 확인: {}", idCheckResult);
		return idCheckResult;
	}
	
	// 기본정보 입력창 (전부 필수입력란) 세션에 저장
	@PostMapping("/sendBasicInfo")
	@ResponseBody
	public String getJoinBasicInfo(@RequestBody Map<String, Object> inputBasicInfo
								 , HttpSession session) {
		Member inputInfo = new Member();
		RefundAccount refundInfo = new RefundAccount();
		//세션에 기본정보 저장하기
		inputInfo.setMemberId(String.valueOf(inputBasicInfo.get("inputId")));
		inputInfo.setMemberPw(String.valueOf(inputBasicInfo.get("inputPw")));
		inputInfo.setMemberName(String.valueOf(inputBasicInfo.get("inputName")));
		inputInfo.setMemberBday(String.valueOf(inputBasicInfo.get("inputBday")));
		inputInfo.setMemberAge(Integer.valueOf((String) inputBasicInfo.get("inputAge")));
		inputInfo.setMemberGender(String.valueOf(inputBasicInfo.get("inputSex")).charAt(0));
		inputInfo.setMemberEmail(String.valueOf(inputBasicInfo.get("inputEmail")));
		inputInfo.setMemberPhone(String.valueOf(inputBasicInfo.get("inputPhone")));
		inputInfo.setMemberPostalCode(String.valueOf(inputBasicInfo.get("inputPostCode")));
		inputInfo.setMemberAddr(String.valueOf(inputBasicInfo.get("inputAddress")));
		inputInfo.setMemberDetailAddr(String.valueOf(inputBasicInfo.get("inputAddressDetail")));
		inputInfo.setMemberCateNum((int)session.getAttribute("memberCate"));
		log.info("member 기본정보란 확인:{}", inputInfo);
		session.setAttribute("basicInfo", inputInfo);
		//세션에 환불정보 저장하기
		refundInfo.setBankName(String.valueOf(inputBasicInfo.get("inputBank")));
		refundInfo.setHolderName(String.valueOf(inputBasicInfo.get("inputAccountOwner")));
		refundInfo.setBankAccountNum(String.valueOf(inputBasicInfo.get("inputAccountNumber")));
		session.setAttribute("refundInfo", refundInfo);
		return "success";
	}
	
	@PostMapping("/join_basic")
	public String getJoinBasicInfo() {
		return "redirect:/join/join_additory";
	}
	
	// 4.추가정보 입력창 (전부 선택입력란)
	@GetMapping("/join_additory")
	public String memberJoinAdditory(HttpSession session, Model model) {
		
		//해시태그 제일 하위분류에서는 이름이 겹치는게 없다는 가정하에 만들었음
		List<Hashtag> tagNames = signupService.getTagNames();
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
	
	// 5. 회원가입 완료 메시지 + 로그인
	@GetMapping("/join_finish")
	public String memberJoinFinish(HttpSession session) {
		signupService.addBasicMembInfo((Member)session.getAttribute("basicInfo"));
		signupService.addRefundInfo((RefundAccount) session.getAttribute("refundInfo"));
		return "/memberJoin/join_finish";
	}
	

}
