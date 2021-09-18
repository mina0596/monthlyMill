package project.monthlyMill.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Member;
import project.monthlyMill.signup.SignupMapper;

@Service
public class LoginService {
	
	//의존성 주입
	private final SignupMapper memberMapper;
	
	@Autowired
	public LoginService(SignupMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	//=======================================서비스 로직 시작======================================
	
	public Member loginCheck(String loginId) {
		return memberMapper.getMemberInfoById(loginId);
	}
	
}
