package project.monthlyMill.common.signup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Member;

@Controller
@RequestMapping("/join")
public class SignupController {
	
	private static final Logger log = LoggerFactory.getLogger(SignupController.class);
	
	@Autowired
	SignupService signupService;
	
	@Autowired
	SMSSender smsSender;

	
	
	// 1.가입방법 (회원가입 첫화면) - 고객, 메이커스 선택
	@GetMapping("/join_method")
	public String memberJoinMethod() {
		return "/memberJoin/join_method";
	}
	
	@PostMapping("/sendMemberCate")
	@ResponseBody
	public Boolean getMemberCate(@RequestParam(name="memberCate", required = false) int memberCate, HttpSession session) {
		session.setAttribute("memberCate", memberCate);
		log.info("joinType 잘 데려오는지 확인:{}", memberCate);
		return true;
	}
	
	@PostMapping("/join_method")
	public String memberJoinCate(HttpSession session) {
		int memberCate = Integer.parseInt(session.getAttribute("memberCate").toString());
		if(memberCate == 2) {
			return "redirect:/join/join_agreement";
		}else{
			return "redirect:/join/makersJoin/join_agreement";
		}
	}
	
	// 2.약관동의
	@GetMapping("/join_agreement")
	public String getMemberJoinAgreement() {
		return "/memberJoin/join_agreement";
	}
	
	@GetMapping("/makersJoin/makerJoin_popup")
	public String makerJoinPopUp() {
		return "/memberJoin/makerJoin_popup";
	}
	
	@GetMapping("/makersJoin/join_agreement")
	public String getMakersJoinAgreement() {
		return "/memberJoin/makerJoin_agreement";
	}
	
	@PostMapping("/sendNewsAgree")
	@ResponseBody
	public Boolean getNewsAgree(@RequestBody HashMap<String,String> params
								, HttpSession session) {
		log.info("선택사항 확인: {}", params );
		session.setAttribute("agreementCheck", params);
		return true;
	}
	
	@PostMapping("/join_agreement")
	public String memberJoinAgreement(HttpSession session) {
		return "redirect:/join/join_basic";
	}
	
	// 메이커스 이용약관 
	@PostMapping("/makersJoin/join_agreement")
	public String getAgreementInfo() {
		return "redirect:/join/makersJoin/join_basic";
	}
	
	// 3.기본정보 입력창 (전부 필수입력란)
	@GetMapping("/join_basic")
	public String memberJoinBasic() {
		return "/memberJoin/join_basic";
	}
	
	// 메이커스 기본 입력창
	@GetMapping("/makersJoin/join_basic")
	public String makersJoinBasic() {
		return "/memberJoin/makerJoin_basic";
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
	public String getJoinBasicInfo(@RequestBody HashMap<String, Object> inputBasicInfo
								 , HttpSession session) {
		log.info("아이디 가져오는것 확인:{}", inputBasicInfo);
		signupService.addBasicMembInfo(inputBasicInfo, session);
		
		return "success";
	}
	
	@PostMapping("/join_basic")
	public String getJoinBasicInfo() {
		
		return "redirect:/join/join_additory";
	}
	
	@PostMapping("/makersJoin/join_basic")
	public String getMakersBasicInfo() {
		return "redirect:/join/makersJoin/makersStoreEnter";
	}
	
	@GetMapping("/makersJoin/makersStoreEnter")
	public String makersStoreEntering() {
		return "/memberJoin/makerJoin_enter";
	}
	
	// 메이커스 입점신청 후 페이지 이동
	@PostMapping("/makersJoin/makersStoreEnter")
	public String getEnteringStoreInfo() {
		return "redirect:/join/makersJoin/join_finish";
	}
	
	
	@GetMapping("/makersJoin/join_finish")
	public String finishMakersJoin(HttpSession session) {
		signupService.signUp((Member) session.getAttribute("basicInfo"));
		return "/memberJoin/makerJoin_finish";
	}
	
	// 메이커스 입점 정보
	@PostMapping("/makersJoin/makersStoreInfo")
	@ResponseBody
	public String getStoreInfo(@RequestBody HashMap<String, Object> params
							 , HttpSession session) {
		log.info("입점신청 후 데이터 확인 :{}", params);
		session.setAttribute("storeInfo", params);
		return "success";
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
		log.info("세션 저장된 정보들 확인 :{}", (Member)session.getAttribute("basicInfo"));
		
		
		// 회원 기본정보 저장하는 sql 실행
		signupService.signUp((Member)session.getAttribute("basicInfo"));
		
		// 관심 해시태그 저장하는 sql 실행
		Member member = (Member) session.getAttribute("basicInfo");
		HashMap<String,Object> preferMap = new HashMap<String,Object>();
		ArrayList<String> tagList = new ArrayList<String>();
		log.info("session에 저장된 태그 list확인:{}", session.getAttribute("preferTags").getClass());
		tagList = (ArrayList<String>) session.getAttribute("preferTags");
		for(int i=0; i<tagList.size(); i++) {
			preferMap.put("hashtagNum", tagList.get(i));
			preferMap.put("memberId", member.getMemberId());
			log.info("for 문 도는 preferMap 정보 돌때마다 확인:{}", preferMap);
			signupService.addPreferTags(preferMap);
		}
		session.invalidate();
		return "/memberJoin/join_finish";
	}
	
	@PostMapping("/getPreferTags")
	@ResponseBody
	public String getPreferTags(@RequestBody ArrayList<String> param, HttpSession session) {
		log.info("ajax로 받아오는 태그명 배열 확인 :{}", param);
		session.setAttribute("preferTags", param);
		return "success";
	}
	
	@GetMapping("/testsms")
	public String TestSms(){
		
		String sendMsg = "테스트입니다";
		String recvNumber ="01056716928";
		
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("contents", sendMsg);
		paramMap.put("phoneNumber", recvNumber);
		paramMap.put("sendType", "random");
		smsSender.sendSms(paramMap);
		return "/memberJoin/join_finish";
		
	
	}
	
	@PostMapping("/sendMsg")
	@ResponseBody
	public Map<String, Object> sendMessage(@RequestBody HashMap<String, Object> paramMap, HttpServletRequest request){
		log.info("받아오는값 확인:{}", paramMap);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//3분 이내 5번 이상 실패했으면 5분 이후에 요청
			int msgCount = 0;
			msgCount = signupService.selectMsgCount(paramMap);
			log.info("msgCount:{}", msgCount);
			if(msgCount >= 5) {
				resultMap.put("isSmsFull", "true");
				return resultMap;
			}
			
			//인증번호 숫자 6자리 포함 문자 전송이면 (sendType random으로 설정하면 됨)
			if(paramMap.containsValue("random")) {
				Random rdm = new Random();
				String numStr = ""; //난수가 저장될 변수
				String rand = "";
				
				for(int i=0; i<6; i++) {
					//0~9까지 난수 생성
					rand = Integer.toString(rdm.nextInt(10));
					numStr += rand;
				}
				paramMap.put("contents", paramMap.get("contents") +"["+ numStr + "]" + "를 입력해주세요.");
				paramMap.put("data", numStr);
				
				//화면에서 휴대폰 번호를 받는다
				// 휴대폰 번호 + 생성된 6자리 난수를 DB에 Table에 등록한다 (SMS_HISTORY)
				//사용자가 인증버튼을 누르면 SMS_HISTORY에서 휴대폰번호와 입력받은 6자리로 SELECT하여 있는지 체크
				//없으면 인증 실패
				//있으면 본인인증 완료 
			}
			
			smsSender.sendSms(paramMap);
			resultMap.put("isSuccess", "true");
		}catch (Exception e) {
			log.error("문자발송 실패: " + e.getMessage());
			resultMap.put("isSucccess", "false");
		}
		return resultMap;
	}
	
	@PostMapping("/phoneNumValidate")
	@ResponseBody
	public HashMap<String, Boolean> validatePhoneNum(@RequestBody HashMap<String, String> paramMap){
		HashMap<String, Boolean> resultMap = new HashMap<String, Boolean>();
		HashMap<String,String> validationInfoMap = signupService.selectValidateNum(paramMap.get("phoneNum"));
		if(validationInfoMap.get("phoneNum").equals(paramMap.get("phoneNum")) && validationInfoMap.get("validationNum").equals(paramMap.get("validationNum"))) {
			resultMap.put("result", true);
		}else {
			resultMap.put("result", false);
		}
		
		return resultMap;
	}

}
