package project.monthlyMill.signup;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Member;
import project.monthlyMill.dto.RefundAccount;

@Mapper
public interface SignupMapper {
	
	// 1.아이디로 회원정보 가져오기
	public Member getMemberInfoById(String inputId);
	
	// 2.회원가입시 기본정보 등록하기
	public void addBasicMembInfo(Member mBasicInfo);
	
	// 3.회원가입시 환불정보 등록하기
	public void addRefundInfo(RefundAccount mRefundInfo);
}
