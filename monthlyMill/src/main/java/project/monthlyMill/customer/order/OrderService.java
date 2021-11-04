package project.monthlyMill.customer.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	// 1. 주문 sequence 가져오기
	public String selectOrderNum(String memberId) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDate = format.format(date);
		String orderNum = 's' + nowDate + '_' + orderMapper.selectOrderSequence(memberId); 
		return orderNum;
	}
		
	// 2. 주문 정보 DB에 저장하기
	public void addOrder(Order orderInfo) {
		orderMapper.addOrder(orderInfo);
	}
	
	// 3. 주문에 대한 입금 확인
	public void updatePaymentConfirm(String memberId) {
		orderMapper.updatePaymentConfirm(memberId);
	}
	

	// 4. 주문 내역 회원아이디로 가져오기
	public List<HashMap<String, Object>> getOrderListByMemberId(String memberId){
		return orderMapper.getOrderListByMemberId(memberId);
	}
	
	// 5. 회원번호로 주문번호 가져왹
	public List<HashMap<String, Object>> getOrderNumByMemberId(String memberId){
		return orderMapper.getOrderNumByMemberId(memberId);
	}
	
	// 6. 주문번호로 주문정보 가져오기
	public List<HashMap<String, Object>> getOrderByOrderNum(String orderNum){
		return orderMapper.getOrderByOrderNum(orderNum);
	}

	// 7. 주문 취소 신청하기
	public void addCancelRequest(HashMap<String, Object> infoMap) {
		if(!infoMap.containsKey("cancelDetailReason")) {
			infoMap.put("cancelDetailReason", "null");
		}
		orderMapper.addCancelRequest(infoMap);
		orderMapper.updateCancelCheck(infoMap.get("orderNum").toString());
	}
	
	// 8. 주문 취소 정보 가져오기
	public List<HashMap<String, Object>> getCancelListByMemberId(String memberId){
		return orderMapper.getCancelListByMemberId(memberId);
	}
	
	// 9. 주문 취소 주문번호만 가져오기
	public List<HashMap<String, Object>> getCanceledOrderNum(String memberId){
		return orderMapper.getCanceledOrderNum(memberId);
	}
	
}
