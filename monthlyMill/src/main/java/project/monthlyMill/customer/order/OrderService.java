package project.monthlyMill.customer.order;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	// 1. 주문 sequence 가져오기
	public String selectOrderNum() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDate = format.format(date);
		String orderNum = 's' + nowDate + '_' + orderMapper.selectOrderSequence(); 
		return orderNum;
	}
		
	// 2. 주문 정보 DB에 저장하기
	public void addOrder(Order orderInfo) {
		orderMapper.addOrder(orderInfo);
	}

	
}
