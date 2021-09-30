package project.monthlyMill.common.login;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.monthlyMill.common.signup.MemberMapper;
import project.monthlyMill.dto.Member;

@Service
public class LoginService {
	private static final Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	MemberMapper signupMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	//=======================================서비스 로직 시작======================================
	
	public Member loginCheck(String loginId) {
		return signupMapper.getMemberInfoById(loginId);
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
