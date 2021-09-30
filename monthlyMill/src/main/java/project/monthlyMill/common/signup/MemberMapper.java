package project.monthlyMill.common.signup;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Member;

@Mapper
public interface MemberMapper {
	
	// 1.아이디로 회원정보 가져오기
	public Member getMemberInfoById(String inputId);
	
	// 2.회원가입시 기본정보 등록하기
	public void addBasicMembInfo(Member mBasicInfo);
	
	// 3.관심태그 등록하기
	public void addPreferTags(HashMap<String,Object> tagInfoMap);
	
}
