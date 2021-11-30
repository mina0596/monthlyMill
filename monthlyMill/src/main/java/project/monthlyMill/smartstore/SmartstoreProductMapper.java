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
}
