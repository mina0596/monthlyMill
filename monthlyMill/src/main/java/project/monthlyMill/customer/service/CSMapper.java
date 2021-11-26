package project.monthlyMill.customer.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.CustomerService;

@Mapper
public interface CSMapper {
	
	
	// 각각의 문의글 정보 가져오기
	public CustomerService getCsPostByCsNum(String csNum);
	
	// CS의 개수
	public int getCSCount();
	
	// CS 문의글 등록
	public void addCS(CustomerService csInfo);
	
	// CS 모든 문의글 가져오기
	public List<CustomerService> getAllCS();
}
