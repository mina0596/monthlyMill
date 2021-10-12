package project.monthlyMill.admin.productRegister;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
	
	// 1. 상품 정보 등록
	public void addProduct(HashMap<String, Object> infoMap);
	
	// 2. 상품 코드 가져오기
	public String getProductCode();
}
