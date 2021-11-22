package project.monthlyMill.customer.order;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.customer.shoppingCart.CartService;
import project.monthlyMill.dto.Order;

@Controller
@RequestMapping("/customer/order")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CartService cartService;
	
	@GetMapping("/requestMatch")
	public String requestMatch(HttpSession session, Model model) {
		String memberId = String.valueOf(session.getAttribute("SID"));
		List<Map<String,String>> cartList = cartService.getCartListByMemberNum(memberId);
		model.addAttribute("cartList", cartList);
		model.addAttribute("totalItemPrice", orderService.getPaymentInfo(memberId, "상품가격합"));
		model.addAttribute("shippingFee", orderService.getPaymentInfo(memberId, "배송비"));
		model.addAttribute("totalPrice", orderService.getPaymentInfo(memberId, "총합"));
		return "/customer/matchRequest";
	}
	
	@GetMapping("/orderList")
	public String getOrderList(Model model, HttpSession session) {
		String memberId = session.getAttribute("SID").toString();
		List<HashMap<String, Object>> orderListByMemberNum = orderService.getOrderListByMemberId(memberId);
		List<HashMap<String, Object>> orderNumList = orderService.getOrderNumByMemberId(memberId);
		
		model.addAttribute("orderList", orderListByMemberNum);
		model.addAttribute("orderNumList", orderNumList);
		log.info("주문리스트 화면에 뿌려주는 주문내역 확인 orderList :{}", orderListByMemberNum);
		log.info("주문리스트 화면에 뿌려주는 주문번호 확인 orderNumList :{}", orderNumList);
		return "/customer/orderList";
	}
	
// 취소 신청양식 화면으로 이동
	@GetMapping("/cancelOrderForm")
	public String cancelOrder(@RequestParam(name = "deleteOrderNum", required = false)String deleteOrderNum
							, Model model) {
		List<HashMap<String,Object>> cancelOrderInfo = orderService.getOrderByOrderNum(deleteOrderNum);
		return "/customer/cancelOrder";
	}
	
	
	
// 취소신청양식에 orderNum 을 이용해 select한 리스트 화면에 보내주기
	@PostMapping("/getCancelInfo")
	@ResponseBody
	public List<HashMap<String, Object>> getCancelInfo(@RequestParam(name = "orderNum", required = false)String orderNum){
		log.info("주문리스트 화면에서 취소버튼 누른 주문번호 받아오는지 확인:{}", orderNum);
		List<HashMap<String, Object>> resultList = orderService.getOrderByOrderNum(orderNum);
		return resultList;
	}
	
// 주문취소 신청하기 버튼 눌러 취소 테이블에 insert
	@PostMapping("/requestRefund")
	@ResponseBody
	public String getRefundRequest(@RequestBody HashMap<String, Object> requestInfo
								, HttpSession session) {
		log.info("주문취소 신청하기 버튼을 누르면 취소에 대한 정보 가져오는지 확인:{}", requestInfo);
		requestInfo.put("memberId", session.getAttribute("SID").toString());
		orderService.addCancelRequest(requestInfo);
		return "success";
	}

// 취소신청 완료하면 화면이동
	@PostMapping("/cancelOrderForm")
	public String finishCancelOrder() {
		return "redirect:/customer/order/cancelOrderList";
	}
	
	@GetMapping("/cancelOrderList")
	public String cancelOrderList(Model model, HttpSession session) {
		String memberId = session.getAttribute("SID").toString();
		List<HashMap<String, Object>> cancelOrderInfo = orderService.getCancelListByMemberId(memberId);
		List<HashMap<String, Object>> cancelOrderNum = orderService.getCanceledOrderNum(memberId);
		
		model.addAttribute("cancelOrderInfo", cancelOrderInfo);
		model.addAttribute("cancelOrderNum", cancelOrderNum);
		return "/customer/cancelOrderList";
	}
	
	@GetMapping("/waitForMatch")
	public String loadingMatch() {
		return "/customer/loadingMatch";
	}
}
