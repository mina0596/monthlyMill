package project.monthlyMill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dao.MemberMapper;
import project.monthlyMill.dto.Member;

@Service
public class LoginService {
	
	//의존성 주입
	private final MemberMapper memberMapper;
	
	@Autowired
	public LoginService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	//=======================================서비스 로직 시작======================================
	
	public Member loginCheck(String loginId) {
		return memberMapper.getMemberInfoById(loginId);
	}
	
}
