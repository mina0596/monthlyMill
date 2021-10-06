package project.monthlyMill.customer.order;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Order;

@Mapper
public interface OrderMapper {
	
	// 1. 주문 sequence 가져오기
	public String selectOrderSequence();
	
	// 2. 주문 정보 DB에 저장하기
	public void addOrder(Order orderInfo);
}
