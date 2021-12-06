package project.monthlyMill.customer.order;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Order;
import project.monthlyMill.dto.Shipment;

@Mapper
public interface OrderMapper {
	
	// 1. 주문 sequence 가져오기
	public String selectOrderSequence(String memberId);
	
	// 2. 주문 정보 DB에 저장하기
	public void addOrder(Order orderInfo);
	
	// 3. 주문에 대한 입금 확인
	public void updatePaymentConfirm(String memberId);
	
	// 4. 회원번호로 주문내역 가져오기
	public List<HashMap<String, Object>> getOrderListByMemberId(String memberId);
	
	// 5. 회원번호로 주문번호 가져오기
	public List<HashMap<String, Object>> getOrderNumByMemberId(String memberId);
	
	// 6. 주문번호로 주문정보 가져오기
	public List<HashMap<String, Object>> getOrderByOrderNum(String orderNum);
	
	// 7. 주문 취소 신청하기
	public void addCancelRequest(HashMap<String, Object> infoMap);
	
	// 8. 주문 취소 정보 가져오기
	public List<HashMap<String, Object>> getCancelListByMemberId(String memberId);
	
	// 9. 주문 취소 주문번호만 가져오기
	public List<HashMap<String, Object>> getCanceledOrderNum(String memberId);
	
	// 10. 주문취소를 주문정보 테이블에서 업데이트해주기
	public void updateCancelCheck(String orderNum);
	
	// 11. 주문시 배송정보 저장하기
	public void addOrderShipInfo(Shipment shipInfo);
	
	// 12. 주문 최종 확인시 update
	public void confirmOrder(String orderNum);
}
