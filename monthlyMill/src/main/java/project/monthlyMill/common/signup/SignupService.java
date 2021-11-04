package project.monthlyMill.common.signup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Member;
import project.monthlyMill.hashtag.HashtagMapper;

@Service
public class SignupService {

	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	HashtagMapper tagMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	SMSMapper smsMapper;
	
	private static final Logger log = LoggerFactory.getLogger(SignupService.class);
	
	//==================================서비스 로직 시작=============================
	
	// 1.회원가입시 아이디 중복체크
	public boolean getMemberInfoById(String inputId) {
		Member memberInfoByIdResult = memberMapper.getMemberInfoById(inputId);
		if(memberInfoByIdResult != null) {
			return false;
		}else{
			return true;
		}
	}
	
	// 2.회원가입시 태그들
	public List<Hashtag> getTagNames() {
		return tagMapper.getHashtag();
	}
	
	// 3.회원가입시 기본정보 등록하기
	public void addBasicMembInfo(Map<String, Object> inputBasicInfo, HttpSession session) {
		Member inputInfo = new Member();
		String encodePassword = passwordEncoder.encode(String.valueOf(inputBasicInfo.get("inputPw")));
		
		inputInfo.setMemberId(String.valueOf(inputBasicInfo.get("inputId")));
		inputInfo.setMemberPw(encodePassword);
		inputInfo.setMemberName(String.valueOf(inputBasicInfo.get("inputName")));
		inputInfo.setMemberBday(String.valueOf(inputBasicInfo.get("inputBday")));
		inputInfo.setMemberAge(Integer.valueOf((String) inputBasicInfo.get("inputAge")));
		inputInfo.setMemberGender(String.valueOf(inputBasicInfo.get("inputSex")).charAt(0));
		inputInfo.setMemberEmail(String.valueOf(inputBasicInfo.get("inputEmail")));
		inputInfo.setMemberPhone(String.valueOf(inputBasicInfo.get("inputPhone")));
		inputInfo.setMemberPostalCode(String.valueOf(inputBasicInfo.get("inputPostCode")));
		inputInfo.setMemberAddr(String.valueOf(inputBasicInfo.get("inputAddress")));
		inputInfo.setMemberDetailAddr(String.valueOf(inputBasicInfo.get("inputAddressDetail")));
		inputInfo.setMemberCateNum(Integer.valueOf((String) inputBasicInfo.get("memberCate")));
		
		
		//세션에 환불정보 저장하기
		inputInfo.setBankName(String.valueOf(inputBasicInfo.get("inputBank")));
		inputInfo.setHolderName(String.valueOf(inputBasicInfo.get("inputAccountOwner")));
		inputInfo.setBankAccountNum(String.valueOf(inputBasicInfo.get("inputAccountNumber")));
		
		
		// 세션에 동의정보 저장하기
		HashMap<String, Object> agreementInfo = (HashMap<String, Object>) session.getAttribute("agreementCheck");
		inputInfo.setInfoOfferAgree(String.valueOf(agreementInfo.get("InfoOfferAgreeCheck")).charAt(0));
		inputInfo.setOutsourceAgree(String.valueOf(agreementInfo.get("outsourceAgreeCheck")).charAt(0));
		inputInfo.setNewsAgree(String.valueOf(agreementInfo.get("newsAgreeCheck")).charAt(0));
		session.setAttribute("basicInfo", inputInfo);
		log.info("세션 기본정보 이후에 저장 확인:{}", session.getAttribute("basicInfo"));
	}
	
	public void signUp(Member mBasicInfo) {
		memberMapper.addBasicMembInfo(mBasicInfo);
	}
	
	// 본인인증 문자 기록 저장
	public void saveHistory(HashMap<String,Object> paramMap) {
		smsMapper.saveHistory(paramMap);
	}
	
	// 3분이내 문자 카운트
	public int selectMsgCount(HashMap<String, Object> phoneNumber) {
		return smsMapper.selectMsgCount(phoneNumber);
	}
	
	// 본인인증 문자 입력 확인
	public HashMap<String, String> selectValidateNum(String phoneNum){
		return smsMapper.selectValidateNum(phoneNum);
	}
	
	// 관심태그 등록하기
	public void addPreferTags(HashMap<String,Object> tagInfoMap) {
		memberMapper.addPreferTags(tagInfoMap);
	}
	
}
