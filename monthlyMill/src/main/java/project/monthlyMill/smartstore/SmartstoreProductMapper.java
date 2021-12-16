package project.monthlyMill.smartstore;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.SmartStoreMember;
import project.monthlyMill.dto.SmartStoreProduct;

@Mapper
public interface SmartstoreProductMapper {
	// ===============================================
	// ====================== 회원 ====================
	// ===============================================
	
	// 회원 아이디로 회원정보 가져오기
	public SmartStoreMember getMemberInfoById(String inputId);
	
	// 회원가입 완료
	public void addMember(HashMap<String, String> memberInfo);
	
	
	
	
	// ===============================================
	// ====================== 상품 ====================
	// ===============================================
	
	
	// 모든 상품 조회
	public List<SmartStoreProduct> getAllProductInfo();
	
	// 상품코드 시퀀스 가져오기
	public String getPcodeSequence();
	
	// 새로운 상품 등록하기
	public void addNewProduct(SmartStoreProduct pInfo);
	
	// 검색조건으로 검색결과 상품정보 가져오기
	public List<SmartStoreProduct> getSearchProductInfo(HashMap<String, String> searchWords);
	
	// 등록된 상품명/상품코드들 가져오기
	public List<HashMap<String, String>> getAllProductName();
	
	// 생산일지에서 상품에 대한 정보 변경시
	public void updateProductionInfo(HashMap<String, Object> updateProductionInfo);
	
	// 상품코드로 상품정보 가져오기
	public SmartStoreProduct getProductInfoByPcode(String pCode);
	
	// 모든 포장재 종류 가져오기
	public List<HashMap<String, String>> getAllWrappingType();
	
	// 모든 상품 구분명 가져오기
	public List<HashMap<String, String>> getAllPName();
}
