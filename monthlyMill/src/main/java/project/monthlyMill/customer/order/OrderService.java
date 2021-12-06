package project.monthlyMill.customer.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.customer.shoppingCart.CartMapper;
import project.monthlyMill.dto.Order;
import project.monthlyMill.dto.Shipment;

@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	CartMapper cartMapper;
	
	// 1. 주문 sequence 가져오기
	public String selectOrderNum(String memberId) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDate = format.format(date);
		String orderNum = 's' + nowDate + '_' + orderMapper.selectOrderSequence(memberId); 
		return orderNum;
	}
		
	// 2. 주문 정보 DB에 저장하기
	public void addOrder(Order orderInfo, Shipment shipInfo) {
		orderMapper.addOrder(orderInfo);
		orderMapper.addOrderShipInfo(shipInfo);
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
	
	
	// 총 계산 금액 계산하기
	public int getPaymentInfo(String memberId, String infoName) {
		List<Map<String,String>> cartList = cartMapper.getCartListByMemberId(memberId);
		int totalItemPrice = 0; 
		for(int i=0; i<cartList.size(); i++) {
			totalItemPrice += (Integer.parseInt(String.valueOf(cartList.get(i).get("pPrice"))) 
					* Integer.parseInt(String.valueOf(cartList.get(i).get("pAmount"))));
		}
		int shippingFee = Integer.parseInt(String.valueOf(cartList.get(0).get("defaultShippingFee")));
		int totalPrice = shippingFee + totalItemPrice;
		
		if(infoName.equals("상품가격합")) {
			return totalItemPrice;
		}else if(infoName.equals("배송비")) {
			return shippingFee;
		}else{
			return totalPrice;
		}
	}
	// 주문 최종 확인시 update
	public void confirmOrder(String orderNum) {
		orderMapper.confirmOrder(orderNum);
	}
}
