package project.monthlyMill.smartstore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.SmartStoreOrder;

@Service
public class SmartstoreService {
	
	@Autowired
	SmartstoreOrderMapper ssMapper;
	
	// 모든 주문 조회
	public List<SmartStoreOrder> getAllOrderInfo(){
		return ssMapper.getAllOrderInfo();
	}
}
