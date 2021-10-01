package project.monthlyMill.customer.shoppingCart;

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

import project.monthlyMill.dto.Cart;

@Controller
@RequestMapping("/customer/cart")
public class CartController {
	
	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	CartService cartService;
	
	// 1.장바구니에 아이템 추가하기
	// 떡추천 화면에서 장바구니 버튼눌렀을때 아이템 추가하기
	@PostMapping("/addItem")
	@ResponseBody
	public HashMap<String,String> addItem(@RequestBody HashMap<String,String> pCode
										, HttpSession session){
		log.info("세션확인:{}", session.getAttribute("SID"));
		log.info("ajax로 pCode넘어오는거 확인:{}", pCode);
		HashMap<String, String> resultMap = new HashMap<String,String>();
		Map<String,Object> addItemInfo = new HashMap<String, Object>();
		if(session.getAttribute("SID")==null) {
			resultMap.put("sessionCheck", "sessionEmpty");
		}else{
			resultMap.put("sessionCheck", "sessionExist");
			addItemInfo.put("memberNum", session.getAttribute("SMNUM"));
			addItemInfo.put("pCode", pCode.get("pCode"));
			cartService.addItem(addItemInfo);
		}
		return resultMap;
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

