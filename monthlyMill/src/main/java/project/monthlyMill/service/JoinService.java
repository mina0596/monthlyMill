package project.monthlyMill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dao.MemberMapper;
import project.monthlyMill.dto.Member;

@Service
public class JoinService {
	
	private final MemberMapper memberMapper;
	
	@Autowired
	public JoinService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	public boolean getMemberInfoById(String inputId) {
		Member memberInfoByIdResult = memberMapper.getMemberInfoById(inputId);
		if(memberInfoByIdResult != null) {
			return false;
		}else{
			return true;
		}
	}
}
