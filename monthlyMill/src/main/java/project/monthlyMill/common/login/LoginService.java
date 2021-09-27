package project.monthlyMill.common.login;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.monthlyMill.common.signup.SignupMapper;
import project.monthlyMill.dto.Member;

@Service
public class LoginService {
	
	//의존성 주입
	private final SignupMapper memberMapper;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public LoginService(SignupMapper memberMapper, PasswordEncoder passwordEncoder) {
		this.memberMapper = memberMapper;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	//=======================================서비스 로직 시작======================================
	
	public Member loginCheck(String loginId) {
		return memberMapper.getMemberInfoById(loginId);
	}
	
	//로그인 후 세션에 저장해두는 로직
	public void saveInSession(Member member, HttpSession session) {
		session.setAttribute("SMNUM", member.getMemberNum());
		session.setAttribute("SID", member.getMemberId());
		session.setAttribute("SNAME", member.getMemberName());
		session.setAttribute("SLEVEL", member.getMemberCateNum());
		session.setAttribute("STEL", member.getMemberPhone());
		session.setAttribute("SADDR", member.getMemberAddr());
		session.setAttribute("SPCODE", member.getMemberPostalCode());

	}
}
