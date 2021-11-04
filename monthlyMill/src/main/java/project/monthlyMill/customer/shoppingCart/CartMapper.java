package project.monthlyMill.customer.shoppingCart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Cart;

@Mapper
public interface CartMapper {
	
	// 1.장바구니에 아이템 추가하기
	public void addItem(HashMap<String,Object> cartInfo);
	
	// 1-1. 추가 전에 아이템있는지 확인
	public Cart selectProductInCart(HashMap<String,Object> infoMap);
	
	// 1-2. 아이템이 존재하면 장바구니 업데이트해주기
	public void updateProductAmount(HashMap<String, Object> infoMap);
	
	// 2. 회원의 장바구니 정보 가져오기
	public List<Map<String,String>> getCartListByMemberId(String memberId);
	
	// 3.장바구니 정보 수정
	public int updateCartByCartNum(HashMap<String,Integer> updateCartInfo);
	
	// 4. 장바구니 번호로 장바구니 정보 가져오기 
	public Cart getCartInfoByCartNum(int cartNum);
	
	// 5. 장바구니 cartNum 으로 삭제하기
	public void deleteCartByCartNum(int cartNum);
}
