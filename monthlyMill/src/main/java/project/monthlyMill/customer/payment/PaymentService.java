package project.monthlyMill.customer.payment;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.customer.shoppingCart.CartMapper;
import project.monthlyMill.dto.Shipment;

@Service
public class PaymentService {

	@Autowired
	CartMapper cartMapper;
	
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
	
	
}
