package project.monthlyMill.smartstore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.SmartStoreOrder;
import project.monthlyMill.dto.SmartStoreProduct;

@Service
public class SmartstoreService {
	
	@Autowired
	SmartstoreOrderMapper oMapper;
	
	@Autowired
	SmartstoreProductMapper pMapper;
	
	
	private static final Logger log = LoggerFactory.getLogger(SmartstoreService.class);

	
	// 모든 주문 조회
	public List<SmartStoreOrder> getAllOrderInfo(){
		return oMapper.getAllOrderInfo();
	}
	
	// 품목코드 생성
	public String getNewPcode() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDate = format.format(date);
		String newPcode = 'p' + nowDate + '_' + pMapper.getPcodeSequence();
		return newPcode;
	}
	
	// 모든 상품 조회
	public List<SmartStoreProduct> getAllProductInfo(){
		return pMapper.getAllProductInfo();
	}
	
	// 상품 등록 처리
	public String getNewProductInfo(HashMap<String, Object> params) {
		// SmartStoreProduct dto 에 정보 넣어주기
		SmartStoreProduct newProductInfo = new SmartStoreProduct();
		newProductInfo.setPCode(params.get("pCode").toString());
		newProductInfo.setPName(params.get("pName").toString());
		newProductInfo.setWrappingType(params.get("wrappingType").toString());
		newProductInfo.setPPrice(Integer.parseInt(params.get("pPrice").toString()));
		String itemName = params.get("itemName").toString();
		if(itemName.equals("item01")) {
			newProductInfo.setItem01(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item02")) {
			newProductInfo.setItem02(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item03")) {
			newProductInfo.setItem03(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item04")) {
			newProductInfo.setItem04(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item05")) {
			newProductInfo.setItem05(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item06")) {
			newProductInfo.setItem06(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item07")) {
			newProductInfo.setItem07(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item08")) {
			newProductInfo.setItem08(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item09")) {
			newProductInfo.setItem09(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item10")) {
			newProductInfo.setItem10(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item11")) {
			newProductInfo.setItem11(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item12")) {
			newProductInfo.setItem12(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item13")) {
			newProductInfo.setItem13(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item14")) {
			newProductInfo.setItem14(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item15")) {
			newProductInfo.setItem15(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item16")) {
			newProductInfo.setItem16(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item17")) {
			newProductInfo.setItem17(Integer.parseInt(params.get("itemQuantity").toString()));
		}else if(itemName.equals("item18")) {
			newProductInfo.setItem18(Integer.parseInt(params.get("itemQuantity").toString()));
		}else {
			newProductInfo.setItem19(Integer.parseInt(params.get("itemQuantity").toString()));
		}
		newProductInfo.setMemo(params.get("memo").toString());
		
		// 새로운 상품 등록하는 쿼리
		try {
			log.info("SmartStoreProduct 에 들어온 데이터 확인해보기: {}", newProductInfo);
			pMapper.addNewProduct(newProductInfo);
			return "success";
		} catch (Exception e) {
			return "fail";
		}
	}
}
