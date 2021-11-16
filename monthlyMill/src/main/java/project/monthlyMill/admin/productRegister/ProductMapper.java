package project.monthlyMill.admin.productRegister;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Product;

@Mapper
public interface ProductMapper {
	
	// 1. 상품 정보 등록
	public void addProduct(Product pInfo);
	
	// 2. 상품 코드 가져오기
	public String getProductCode();
	
	// 3. 상품 코드로 상품정보 수정
	public void updatePInfoByPCode(Product pInfo);
}
