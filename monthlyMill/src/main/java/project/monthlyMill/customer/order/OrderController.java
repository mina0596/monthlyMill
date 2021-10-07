package project.monthlyMill.customer.order;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.monthlyMill.dto.Order;

@Controller
@RequestMapping("/customer/order")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	OrderService orderService;
	
	@GetMapping("/orderList")
	public String getOrderList(Model model, HttpSession session) {
		int memberNum = Integer.valueOf(session.getAttribute("SMNUM").toString());
		List<HashMap<String, Object>> orderListByMemberNum = orderService.getOrderListByMemberNum(memberNum);
		List<HashMap<String, Object>> orderNumList = orderService.getOrderNumByMemberNum(memberNum);
		
		model.addAttribute("orderList", orderListByMemberNum);
		model.addAttribute("orderNumList", orderNumList);
		log.info("주문리스트 화면에 뿌려주는 주문내역 확인 orderList :{}", orderListByMemberNum);
		log.info("주문리스트 화면에 뿌려주는 주문번호 확인 orderNumList :{}", orderNumList);
		return "/customer/orderList";
	}
	
	@GetMapping("/cancelOrderForm")
	public String cancelOrder(@RequestParam(name = "deleteOrderNum", required = false)String deleteOrderNum
							, Model model) {
		log.info("주문리스트 화면에서 취소버튼 누른 주문번호 받아오는지 확인:{}", deleteOrderNum);
		List<HashMap<String,Object>> cancelOrderInfo = orderService.getOrderByOrderNum(deleteOrderNum);
		model.addAttribute("info", cancelOrderInfo);
		return "/customer/cancelOrder";
	}
	
	@GetMapping("/cancelOrderList")
	public String cancelOrderList() {
		return "/customer/cancelOrderList";
	}
	
	@GetMapping("/waitForMatch")
	public String loadingMatch() {
		return "/customer/loadingMatch";
	}
}
