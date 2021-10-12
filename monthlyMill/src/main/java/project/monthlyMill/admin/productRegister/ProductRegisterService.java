package project.monthlyMill.admin.productRegister;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRegisterService {
	
	@Autowired
	ProductMapper productMapper;
	
	// 1. 상품 등록 하기
	
	// 2. 새로운 상품 코드 가져오기
	public String getProductCode() {
		return productMapper.getProductCode();
	}
	
}
