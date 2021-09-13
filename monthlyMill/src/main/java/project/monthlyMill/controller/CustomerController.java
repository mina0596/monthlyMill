package project.monthlyMill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping("/payment")
	public String payment() {
		return "/customer/payment";
	}
	
	@GetMapping("/loading")
	public String matchLoading() {
		return "/customer/loading";
	}
	
	@GetMapping("/orderList")
	public String getOrderList() {
		return "/customer/orderList";
	}
	
	@GetMapping("/payment_deposit")
	public String getPaymentDeposit() {
		return "/customer/payment_deposit";
	}
	
	@GetMapping("/payment_confirm")
	public String confirmPayment() {
		return "/customer/payment_confirm";
	}
}	
