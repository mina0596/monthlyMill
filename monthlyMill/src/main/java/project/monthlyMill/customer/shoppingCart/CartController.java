package project.monthlyMill.customer.shoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.dto.Cart;

@Controller
@RequestMapping("/customer/cart")
public class CartController {
	
	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	// 1.장바구니에 아이템 추가하기
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
	
	// 2.장바구니 정보 가져오기
	@GetMapping("/cartList")
	public String getCartList(Model model, HttpSession session) {
		String memberNum = String.valueOf(session.getAttribute("SMNUM"));
		List<Map<String,String>> cartList = cartService.getCartListByMemberId(memberNum);
		model.addAttribute("cartList", cartList);
		return "/customer/cart";
	}
	
	// 3.장바구니 정보 수정
	@PostMapping("/itemCartInfo")
	@ResponseBody
	public String getCartInfo(@RequestBody Map<String,Object> cartInfo) {
		log.info("카트 정보 받아오는거 확인하기 cartInfo:{}", cartInfo);
		List<String> cartNum = (List<String>) cartInfo.get("cartNum");
		List<String> pAmount = (List<String>) cartInfo.get("pAmount");
		log.info("cartNum 넘어오는 배열 확인:{}", cartNum);
		
		for(int i=0; i<cartNum.size(); i++) {
			Map<String, Integer> cartInfoMap = new HashMap<String, Integer>();
			
			cartInfoMap.put("pAmount", Integer.parseInt(pAmount.get(i)));
			cartInfoMap.put("cartNum", Integer.parseInt(cartNum.get(i)));
			cartService.updateCartByCartNum(cartInfoMap);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

