package project.monthlyMill.customer.shoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Cart;

@Service
public class CartService {
	
	@Autowired
	CartMapper cartMapper;
	
	// 1.장바구니에 아이템 추가하기 
	public void addItem(HashMap<String,Object> cartInfo) {
		Cart checkProductInCart = cartMapper.selectProductInCart(cartInfo);
		if(checkProductInCart == null) {
			cartMapper.addItem(cartInfo);
		}else {
			int cartNum = checkProductInCart.getCartNum();
			int pAmount = checkProductInCart.getpAmount();
			HashMap<String, Object> infoMap = new HashMap<String, Object>();
			infoMap.put("pAmount", pAmount+1);
			infoMap.put("cartNum", cartNum);
			cartMapper.updateProductAmount(infoMap);
		}
	}
	
	// 2.회원번호로 회원의 장바구니 정보 가져오기
	public List<Map<String,String>> getCartListByMemberNum(String memberId){
		
		return cartMapper.getCartListByMemberId(memberId);
	}
	
	// 3.장바구니 정보 수정
	public int updateCartByCartNum(HashMap<String,Integer> updateCartInfo) {
		return cartMapper.updateCartByCartNum(updateCartInfo);
	}
	
	// 4. 장바구니 번호로 장바구니 정보 가져오기
	public Cart getCartInfoByCartNum(int cartNum){
		return cartMapper.getCartInfoByCartNum(cartNum);
	}
	
	// 5. 장바구니 cartNum 으로 삭제하기
	public void deleteCartByCartNum(int cartNum) {
		cartMapper.deleteCartByCartNum(cartNum);
	}
}
