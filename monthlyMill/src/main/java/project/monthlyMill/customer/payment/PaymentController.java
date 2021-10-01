package project.monthlyMill.customer.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.customer.shoppingCart.CartService;

@Controller
@RequestMapping("/customer/payment")
public class PaymentController {
	
	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	CartService cartService;
	
	// 1. 장바구니에서 결제하기 넘어가는 정보 받아 뿌려주기
	@GetMapping("/paymentInfo")
	public String getPaymentInfo(Model model, HttpSession session) {
		List<Map<String,String>> cartList = cartService.getCartListByMemberId(String.valueOf(session.getAttribute("SMNUM")));
		model.addAttribute("cartList", cartList);
		
		// 총 계산금액 계산하기
		int totalItemPrice = 0; 
		for(int i=0; i<cartList.size(); i++) {
			totalItemPrice += (Integer.parseInt(String.valueOf(cartList.get(i).get("pPrice"))) 
					* Integer.parseInt(String.valueOf(cartList.get(i).get("pAmount"))));
		}
		int shippingFee = Integer.parseInt(String.valueOf(cartList.get(0).get("defaultShippingFee")));
		model.addAttribute("totalItemPrice", totalItemPrice);
		model.addAttribute("shippingFee", shippingFee);
		model.addAttribute("totalPrice", totalItemPrice + shippingFee);
		
		return "/customer/payment";
	}
	
	// 2. 주문자와 동일버튼 누른 후 화면에 정보 뿌려주기 (로그인일때만 가능! 세션에서 가져오는거라)
	@PostMapping("/getMemberInfo")
	@ResponseBody
	public HashMap<String,Object> getMemeberInfo(HttpSession session){
		HashMap<String,Object> memberInfoMap = new HashMap<String,Object>();
		memberInfoMap.put("mAddr", session.getAttribute("SADDR"));
		memberInfoMap.put("mDetailAddr", session.getAttribute("SDADDR"));
		memberInfoMap.put("mPostalCode", session.getAttribute("SPCODE"));
		
		return memberInfoMap;
	}
	
	// 3. 주문에 대한 정보 ajax로 데려오기
	@PostMapping("/getOrderInfo")
	@ResponseBody
	public String getOrderInfo(@RequestBody HashMap<String, Object> params) {
			log.info("**************************************");
			log.info("**************************************");
			log.info("화면에서 받아오는 주문 정보 확인하기 :{}", params);
			log.info("**************************************");
			log.info("**************************************");
		return "success";
	}
	
	// 4. 결제하기 버튼 누르면 화면 이동
	@PostMapping("/paymentInfo")
	public String completePaymentInfo() {
		return "customer/payment_deposit";
	}
	
	@GetMapping("/payment_deposit")
	public String paymentDeposit() {
		return "/customer/payment_deposit";
	}
	
	@GetMapping("/payment_confirm")
	public String paymentConfirm() {
		return "/customer/payment_confirm";
	}
}
