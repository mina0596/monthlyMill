package project.monthlyMill.dao;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Member;

@Mapper
public interface MemberMapper {
	
	public Member getMemberInfoById(String inputId);
}
