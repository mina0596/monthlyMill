package project.monthlyMill.smartstore;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.SmartStoreProduct;

@Mapper
public interface SmartstoreProductMapper {
	
	// 모든 상품 조회
	public List<SmartStoreProduct> getAllProductInfo();
}
