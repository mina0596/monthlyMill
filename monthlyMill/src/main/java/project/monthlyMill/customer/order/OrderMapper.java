package project.monthlyMill.customer.order;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Order;

@Mapper
public interface OrderMapper {
	
	// 1. 주문 sequence 가져오기
	public String selectOrderSequence(int memberNum);
	
	// 2. 주문 정보 DB에 저장하기
	public void addOrder(Order orderInfo);
	
	// 3. 주문에 대한 입금 확인
	public void updatePaymentConfirm(int memberNum);
	
	// 4. 회원번호로 주문내역 가져오기
	public List<HashMap<String, Object>> getOrderListByMemberNum(int memberNum);
	
	// 5. 회원번호로 주문번호 가져오기
	public List<HashMap<String, Object>> getOrderNumByMemberNum(int memberNum);
	
	// 6. 주문번호로 주문정보 가져오기
	public List<HashMap<String, Object>> getOrderByOrderNum(String orderNum);
	
	// 7. 주문 취소 신청하기
	public void addCancelRequest(HashMap<String, Object> infoMap);
	
	// 8. 주문 취소 정보 가져오기
	public List<HashMap<String, Object>> getCancelListByMemberNum(int memberNum);
	
	// 9. 주문 취소 주문번호만 가져오기
	public List<HashMap<String, Object>> getCanceledOrderNum(int memberNum);
}
