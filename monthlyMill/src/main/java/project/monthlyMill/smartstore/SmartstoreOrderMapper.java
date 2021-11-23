package project.monthlyMill.smartstore;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.SmartStoreOrder;

@Mapper
public interface SmartstoreOrderMapper {
	
	// 모든 주문 조회
	public List<SmartStoreOrder> getAllOrderInfo(); 
}
