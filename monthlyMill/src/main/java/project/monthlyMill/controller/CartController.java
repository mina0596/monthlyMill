package project.monthlyMill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/customer/cart")
public class CartController {
	
	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	
	@GetMapping("/cartList")
	public String getCartList() {
		return "/customer/cart";
	}
	
	@GetMapping("/addItem")
	public String addItem(@RequestParam(name = "pCode", required = false) String addPcode) {
		log.info("addPcode 확인:{}", addPcode);
		
		return "/customer/cart";
	}
}
