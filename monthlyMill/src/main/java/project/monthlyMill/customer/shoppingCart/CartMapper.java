package project.monthlyMill.customer.shoppingCart;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
	
	// 1.장바구니에 아이템 추가하기
	public int addItem(Map<String,Object> cartInfo);
	
	// 2.장바구니 정보 가져오기
	public List<Map<String,String>> getCartListByMemberId(String memberNum);
	
	// 3.장바구니 정보 수정
	public int updateCartByCartNum(Map<String,Integer> updateCartInfo);
}
