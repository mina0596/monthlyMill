package project.monthlyMill.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.monthlyMill.service.CartService;

@Controller
@RequestMapping("/customer/payment")
public class PaymentController {
	
	private static final Logger log = LoggerFactory.getLogger(PaymentController.class);
	private final CartService cartService;
	
	public PaymentController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping("/paymentInfo")
	public String getPaymentInfo(Model model, HttpSession session) {
		List<Map<String,String>> cartList = cartService.getCartListByMemberId(String.valueOf(session.getAttribute("SMNUM")));
		model.addAttribute("cartList", cartList);
		return "/customer/payment";
	}
}
