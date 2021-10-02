package project.monthlyMill.customer.shoppingCart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
	
	@Autowired
	CartMapper cartMapper;
	
	// 1.장바구니에 아이템 추가하기 
	public void addItem(Map<String,Object> cartInfo) {
		cartMapper.addItem(cartInfo);
	}
	
	
	// 2.장바구니 정보 가져오기
	public List<Map<String,String>> getCartListByMemberNum(String memberNum){
		
		return cartMapper.getCartListByMemberNum(memberNum);
	}
	
	// 3.장바구니 정보 수정
	public int updateCartByCartNum(Map<String,Integer> updateCartInfo) {
		return cartMapper.updateCartByCartNum(updateCartInfo);
	}
}
