package project.monthlyMill.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.monthlyMill.service.CartService;

@Controller
@RequestMapping("/customer/cart")
public class CartController {
	
	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping("/cartList")
	public String getCartList() {
		return "/customer/cart";
	}
	
	@GetMapping("/addItem")
	public String addItem(@RequestParam(name = "pCode", required = false) String addPcode
						, HttpSession session) {
		log.info("addPcode 확인:{}", addPcode);
		Map<String,Object> addItemInfo = new HashMap<String, Object>();
		addItemInfo.put("memberNum", session.getAttribute("SMNUM"));
		addItemInfo.put("pCode", addPcode);
		log.info("map에 넣은 값들 확인:{}", addItemInfo);
		cartService.addItem(addItemInfo);
		return "/customer/cart";
	}
}
