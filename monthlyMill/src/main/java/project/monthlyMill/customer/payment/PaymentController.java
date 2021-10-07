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

import project.monthlyMill.customer.order.OrderService;
import project.monthlyMill.customer.shoppingCart.CartMapper;
import project.monthlyMill.customer.shoppingCart.CartService;
import project.monthlyMill.dto.Cart;
import project.monthlyMill.dto.Order;

@Controller
@RequestMapping("/customer/payment")
public class PaymentController {
	
	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderService orderService;
	
	// 1. 장바구니에서 결제하기 넘어가는 정보 받아 뿌려주기
	@GetMapping("/paymentInfo")
	public String getPaymentInfo(Model model, HttpSession session) {
		String memberNum = String.valueOf(session.getAttribute("SMNUM"));
		List<Map<String,String>> cartList = cartService.getCartListByMemberNum(memberNum);
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalItemPrice", paymentService.getPaymentInfo(memberNum, "상품가격합"));
		model.addAttribute("shippingFee", paymentService.getPaymentInfo(memberNum, "배송비"));
		model.addAttribute("totalPrice", paymentService.getPaymentInfo(memberNum, "총합"));
		
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
	public String getOrderInfo(@RequestBody HashMap<String, Object> params, HttpSession session) {
			log.info("**************************************");
			log.info("**************************************");
			log.info("화면에서 받아오는 주문 정보 확인하기 :{}", params);
			log.info("**************************************");
			log.info("**************************************");
			List<String> cartList = (List<String>) params.get("cartNum");
			
			Order orderInfo = new Order();
			orderInfo.setMemberNum(Integer.parseInt(session.getAttribute("SMNUM").toString()));
			orderInfo.setReceiverAddr(String.valueOf(params.get("receiverAddr")));
			orderInfo.setReceiverName(String.valueOf(params.get("receiverName")));
			orderInfo.setReceiverPhoneNum(String.valueOf(params.get("receiverPhone")));
			orderInfo.setReceiverPostalCode(String.valueOf(params.get("receiverPostalCode")));
			orderInfo.setReceiverDetailAddr(String.valueOf(params.get("receiverDetailAddr")));
			orderInfo.setOrderNum(orderService.selectOrderNum(Integer.parseInt(session.getAttribute("SMNUM").toString())));
			for(int i=0; i<cartList.size(); i++) {
				orderInfo.setCartNum(Integer.parseInt(cartList.get(i)));
				Cart cartInfo = cartService.getCartInfoByCartNum(Integer.parseInt(cartList.get(i)));
				orderInfo.setpCount(cartInfo.getpAmount());
				orderInfo.setpCode(cartInfo.getpCode());
				orderService.addOrder(orderInfo);
			}
		return "success";
	}
	
	// 4. 결제하기 버튼 누르면 화면 이동
	@PostMapping("/paymentInfo")
	public String completePaymentInfo() {
		return "redirect:/customer/payment/paymentDeposit";
	}
	
	// 5. 결제하기 화면으로 이동
	@GetMapping("/paymentDeposit")
	public String paymentDeposit(HttpSession session, Model model) {
		String memberNum = String.valueOf(session.getAttribute("SMNUM"));
		List<Map<String,String>> cartList = cartService.getCartListByMemberNum(memberNum);
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalPrice", paymentService.getPaymentInfo(memberNum, "총합"));
		return "/customer/payment_deposit";
	}
	
	// 6. 입금정보 ajax로 데려오기
	@PostMapping("/getDepositInfo")
	@ResponseBody
	public String getDepositInfo(@RequestBody HashMap<String, Object> paramMap) {
		log.info("getDepositInfo 받아오는 데이터 확인:{}", paramMap);
		return "success";
	}
	
	@PostMapping("/paymentDeposit")
	public String paymentDeposit(HttpSession session) {
		int memberNum = Integer.valueOf(session.getAttribute("SMNUM").toString());
		orderService.updatePaymentConfirm(memberNum);
		return "redirect:/customer/payment/loading";
	}
	
	// 7. 입금 확인 되었다는 메세지 페이지
	@GetMapping("/paymentConfirm")
	public String paymentConfirm() {
		
		return "/customer/payment_confirm";
	}
	
	@GetMapping("/loading")
	public String loading() {
		return "/customer/loading";
	}
	
}
