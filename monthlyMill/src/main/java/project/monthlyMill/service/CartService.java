package project.monthlyMill.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import project.monthlyMill.dao.CartMapper;

@Service
public class CartService {
	
	private final CartMapper cartMapper;
	
	public CartService(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}
	
	public int addItem(Map<String,String> cartInfo) {
		
		return cartMapper.addItem(cartInfo);
	}
}
