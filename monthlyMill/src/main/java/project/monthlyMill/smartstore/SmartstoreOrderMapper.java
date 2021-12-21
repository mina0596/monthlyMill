package project.monthlyMill.smartstore;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.SmartStoreOrder;

@Mapper
public interface SmartstoreOrderMapper {
	
	// 모든 주문 조회
	public List<SmartStoreOrder> getAllOrderInfo(); 
	
	// 주문 등록
	public void addOrder(SmartStoreOrder orderInfo);
	
	// 주문번호 가져오기
	public String getOrderSequence();
	
	// 날짜별로 주문 정보 가져오기
	public List<SmartStoreOrder> getOrderInfoByDate(String expDate);
	
	// 생산일지 정보 가져오기
	public List<HashMap<String, Object>> getProductionInfo(String expDate);
	
	// 생산일지에서 날짜별 생산해야할 각각의 품목 수
	public HashMap<String, Integer> getItemsTotal(String expDate);
	
	// 주문정보를 수정시
	public void updateOrderInfo(HashMap<String, Object> updateOrderInfo);
	
	// 주문테이블의 idx로 주문정보 가져오기
	public SmartStoreOrder getOrderInfoByIdx(int orderIdx);
	
	// 주문수정되면 원본 주문 테이블에 modifyCheck 바꿔주기
	public void updateModifiedCheck(int orderIdx);
	
	// 주문정보 두번이상 바꿨을 경우
	public void updateOrderMultiple(HashMap<String, Object> updateInfo);
}
