package project.monthlyMill.customer.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	// 1. 주문 sequence 가져오기
	public String selectOrderSequence() {
		return orderMapper.selectOrderSequence();
	}
		
	// 2. 주문 정보 DB에 저장하기
	public void addOrder(Order orderInfo) {
		orderMapper.addOrder(orderInfo);
	}

	
}
