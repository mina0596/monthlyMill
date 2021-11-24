package project.monthlyMill.smartstore;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.SmartStoreProduct;

@Mapper
public interface SmartstoreProductMapper {
	
	// 모든 상품 조회
	public List<SmartStoreProduct> getAllProductInfo();
	
	// 상품코드 시퀀스 가져오기
	public String getPcodeSequence();
	
	// 새로운 상품 등록하기
	public void addNewProduct(SmartStoreProduct pInfo);
}
