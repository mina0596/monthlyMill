package project.monthlyMill.customer;

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
	
	
	@GetMapping("/loading")
	public String matchLoading() {
		return "/customer/loading";
	}
	
	@GetMapping("/orderList")
	public String getOrderList() {
		return "/customer/orderList";
	}
	
}	
