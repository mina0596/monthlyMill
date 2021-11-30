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
	
	// 생산일지 정보 가져오기
	public List<HashMap<String, Object>> getProductionInfo(String expDate);
	
	// 생산일지에서 날짜별 생산해야할 각각의 품목 수
	public HashMap<String, Integer> getItemsTotal(String expDate);
}
