package project.monthlyMill.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import project.monthlyMill.dao.CartMapper;

@Service
public class CartService {
	
	private final CartMapper cartMapper;
	
	public CartService(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}
	
	// 1.장바구니에 아이템 추가하기 
	public int addItem(Map<String,Object> cartInfo) {
		return cartMapper.addItem(cartInfo);
	}
	
	
	// 2.장바구니 정보 가져오기
	public List<Map<String,String>> getCartListByMemberId(String memberNum){
		
		return cartMapper.getCartListByMemberId(memberNum);
	}
	
	// 3.장바구니 정보 수정
	public int updateCartByCartNum(Map<String,Integer> updateCartInfo) {
		return cartMapper.updateCartByCartNum(updateCartInfo);
	}
}
