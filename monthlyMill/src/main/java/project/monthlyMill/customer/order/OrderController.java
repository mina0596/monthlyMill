package project.monthlyMill.customer.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/order")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@GetMapping("/orderList")
	public String getOrderList(Model model) {
		return "/customer/orderList";
	}
	
	@GetMapping("/orderCancel")
	public String cancelOrder() {
		return "/customer/cancelOrder";
	}
	
	@GetMapping("/cancelOrderList")
	public String cancelOrderList() {
		return "/customer/cancelOrderList";
	}
}
