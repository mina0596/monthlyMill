package project.monthlyMill.admin.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.common.signup.MemberMapper;
import project.monthlyMill.dto.Member;

@Service
public class AdminCustomerService {
	
	@Autowired
	MemberMapper mMapper;
	
	// 모든 회원 정보 가져오기
	public List<Member> getAllMemberInfo(){
		return mMapper.getAllMemberInfo();
	}
}
