package project.monthlyMill.smartstore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.SmartStoreMember;
import project.monthlyMill.dto.SmartStoreOrder;
import project.monthlyMill.dto.SmartStoreProduct;

@Service
public class SmartstoreService {
	
	@Autowired
	SmartstoreOrderMapper oMapper;
	
	@Autowired
	SmartstoreProductMapper pMapper;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	private static final Logger log = LoggerFactory.getLogger(SmartstoreService.class);
	
	
	
	// *****************************************************
	// =========================== 회원가입 =====================
	// *****************************************************
	
	// 회원가입시 아이디 중복 확인
	public boolean idDupCheck(String inputId) {
		SmartStoreMember idCheck = pMapper.getMemberInfoById(inputId);
		if(idCheck != null) {
			// 아이디 사용중
			return false;
		}else {
			// 아이디 사용 가능
			return true;
		}
	}
	
	// 회원가입 완료
	public void addMember(HashMap<String, String> memberInfo) {
		memberInfo.put("encodedPw", pwEncoder.encode(memberInfo.get("inputPw")));
		pMapper.addMember(memberInfo);
	}
	
	
	
	
	
	// *****************************************************
	// =========================== 로그인 =====================
	// *****************************************************

	public boolean loginCheck(HashMap<String, String> loginInfo, HttpSession session) {
		SmartStoreMember memberInfo = pMapper.getMemberInfoById(loginInfo.get("loginId"));
		if(pwEncoder.matches(loginInfo.get("loginPw"), memberInfo.getMPw())) {
			session.setAttribute("SESSIONID", loginInfo.get("loginId"));
			session.setAttribute("SESSIONNAME", memberInfo.getMName());
			session.setAttribute("SESSIONDEPT", memberInfo.getMDep());
			session.setAttribute("SESSIONPHONE", memberInfo.getMPhone());
			session.setAttribute("SESSIONEMAIL", memberInfo.getMEmail());
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
	
	// *****************************************************
	// =========================== 주문 =====================
	// *****************************************************

	
	// 모든 주문 조회
	public List<SmartStoreOrder> getAllOrderInfo(){
		return oMapper.getAllOrderInfo();
	}

	// 날짜별로 주문 정보 가져오기
	public List<SmartStoreOrder> getOrderInfoByDate(String expDate){
		return oMapper.getOrderInfoByDate(expDate);
	}
	
	// 주문코드 생성
	public String getNewOrderNum() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDate = format.format(date);
		String newOrderNum = nowDate + oMapper.getOrderSequence();
		return newOrderNum;
	}
	
	// 새로운 주문 등록
	public void addNewOrder(HashMap<String, Object> newOrderInfo, HttpSession session) {
		SmartStoreOrder orderInfo = new SmartStoreOrder();
		
		// 1. 상품이 여러개인지 한개인지 확인
		int orderedProductNum = Integer.parseInt(newOrderInfo.get("orderedProductNum").toString());
		
		// 상품에 관계없이 들어가야하는 정보 
		orderInfo.setOrderNum(newOrderInfo.get("orderNum").toString());
		orderInfo.setShippingDate(newOrderInfo.get("shippingDate").toString());
		orderInfo.setPaidDate(newOrderInfo.get("paidDate").toString());
		orderInfo.setShippingMethod(newOrderInfo.get("shippingMethod").toString());
		orderInfo.setOrdererName(newOrderInfo.get("ordererName").toString());
		orderInfo.setOrderderPhone(newOrderInfo.get("orderderPhone").toString());
		orderInfo.setRegMId(session.getAttribute("SESSIONID").toString());
		
		// 2. 상품이 한개라면,
		if(orderedProductNum == 1) {
			orderInfo.setReceiverName(newOrderInfo.get("receiverName").toString());
			orderInfo.setReceiverPhone(newOrderInfo.get("receiverPhone").toString());
			orderInfo.setProductName(newOrderInfo.get("productName").toString());
			orderInfo.setProductCode(newOrderInfo.get("productCode").toString());
			orderInfo.setDetailedProduct(newOrderInfo.get("detailedProduct").toString());
			orderInfo.setOrderQuantity(Integer.parseInt(newOrderInfo.get("orderQuantity").toString()));
			orderInfo.setTotOrderAmount(Integer.parseInt(newOrderInfo.get("totOrderAmount").toString()));
			orderInfo.setOption(newOrderInfo.get("option").toString());
			orderInfo.setShippingAddr(newOrderInfo.get("shippingAddr").toString());
			orderInfo.setDeliveryMsg(newOrderInfo.get("deliveryMsg").toString());
			orderInfo.setShippingFee(Integer.parseInt(newOrderInfo.get("shippingFee").toString()));
			orderInfo.setOrderType(newOrderInfo.get("orderType").toString());
			orderInfo.setExpDeliveryDate(newOrderInfo.get("expDeliveryDate").toString());
			oMapper.addOrder(orderInfo);
			
		// 3. 상품이 여러개라면,
		}else {
			List<String> receiverName = (List<String>) newOrderInfo.get("receiverName");
			List<String> receiverPhone = (List<String>) newOrderInfo.get("receiverPhone");
			List<String> productName = (List<String>) newOrderInfo.get("productName");
			List<String> productCode = (List<String>) newOrderInfo.get("productCode");
			List<String> detailedProduct = (List<String>) newOrderInfo.get("detailedProduct");
			List<String> orderQuantity = (List<String>) newOrderInfo.get("orderQuantity");
			List<String> totOrderAmount = (List<String>) newOrderInfo.get("totOrderAmount");
			List<String> option = (List<String>) newOrderInfo.get("option");
			List<String> shippingAddr = (List<String>) newOrderInfo.get("shippingAddr");
			List<String> deliveryMsg = (List<String>) newOrderInfo.get("deliveryMsg");
			List<String> shippingFee = (List<String>) newOrderInfo.get("shippingFee");
			List<String> orderType = (List<String>) newOrderInfo.get("orderType");
			List<String> expDeliveryDate = (List<String>) newOrderInfo.get("expDeliveryDate");
			
			for(int i=0; i < orderedProductNum; i++) {
				orderInfo.setReceiverName(receiverName.get(i));
				orderInfo.setReceiverPhone(receiverPhone.get(i));
				orderInfo.setProductName(productName.get(i));
				orderInfo.setProductCode(productCode.get(i));
				orderInfo.setDetailedProduct(detailedProduct.get(i));
				orderInfo.setOrderQuantity(Integer.parseInt(orderQuantity.get(i)));
				orderInfo.setTotOrderAmount(Integer.parseInt(totOrderAmount.get(i)));
				orderInfo.setOption(option.get(i));
				orderInfo.setShippingAddr(shippingAddr.get(i));
				orderInfo.setDeliveryMsg(deliveryMsg.get(i));
				orderInfo.setShippingFee(Integer.parseInt(shippingFee.get(i)));
				orderInfo.setOrderType(orderType.get(i));
				orderInfo.setExpDeliveryDate(expDeliveryDate.get(i));
				oMapper.addOrder(orderInfo);
			}
		}
	}
	
	// 생산일지 정보 가져오기
	public List<HashMap<String, Object>> getProductionInfo(String expDate){
		return oMapper.getProductionInfo(expDate);
	}
	
	// 생산일지에서 날짜별 생산해야할 각각의 품목 수
	public HashMap<String, Integer> getItemsTotal(String expDate){
		return oMapper.getItemsTotal(expDate);
	}
	
	// 주문정보를 수정시
	public void updateProductionInfo(HashMap<String, Object> updateInfo) {
		
		String newPCode = getNewPcode();
		int pPrice = pMapper.getProductInfoByPcode(updateInfo.get("productCode").toString()).getPPrice();
		
		// idx 로 order정보 가져오기
		SmartStoreOrder orderInfoByIdx = oMapper.getOrderInfoByIdx(Integer.parseInt(updateInfo.get("orderIdx").toString()));
		
		
		String shippingDate = orderInfoByIdx.getShippingDate();
		String paidDate = orderInfoByIdx.getPaidDate();
		String ordererName = orderInfoByIdx.getOrdererName();
		String ordererPhone = orderInfoByIdx.getOrderderPhone();
		String receiverPhone = orderInfoByIdx.getReceiverPhone();
		String option = orderInfoByIdx.getOption();
		int shippingFee = orderInfoByIdx.getShippingFee();
		String shippingAddr = orderInfoByIdx.getShippingAddr();
		String deliveryMsg = orderInfoByIdx.getDeliveryMsg();
		String orderType = orderInfoByIdx.getOrderType();
		String memo = orderInfoByIdx.getMemo();
		String productName = orderInfoByIdx.getProductName();
		
		
		int totOrderAmount = pPrice * Integer.parseInt(updateInfo.get("orderQuantity").toString());
		// 주문에 대한 사항인지 상품에 대한 사항인지 파악하기
		if(updateInfo.containsKey("shippingPayCheck")) {
			
			// 1. 주문 + 상품 모두 수정
			if(updateInfo.containsKey("item01")) {
				updateInfo.put("pCode", newPCode);
				updateInfo.put("pPrice", pPrice);
				
				
				
				updateInfo.put("shippingDate", shippingDate);
				updateInfo.put("paidDate", paidDate);
				updateInfo.put("ordererName", ordererName);
				updateInfo.put("ordererPhone", ordererPhone);
				updateInfo.put("receiverPhone", receiverPhone);
				updateInfo.put("totOrderAmount", totOrderAmount);
				updateInfo.put("option", option);
				updateInfo.put("shippingFee", shippingFee);
				updateInfo.put("shippingAddr", shippingAddr);
				updateInfo.put("deliveryMsg", deliveryMsg);
				updateInfo.put("orderType", orderType);
				updateInfo.put("productName", productName);
				updateInfo.put("memo", memo);
				updateInfo.put("modifiedCheck", 'Y');
				
				
				oMapper.updateOrderInfo(updateInfo);
				pMapper.updateProductionInfo(updateInfo);
				
			// 2. 주문만 수정
			}else {
				updateInfo.put("shippingDate", shippingDate);
				updateInfo.put("paidDate", paidDate);
				updateInfo.put("ordererName", ordererName);
				updateInfo.put("ordererPhone", ordererPhone);
				updateInfo.put("receiverPhone", receiverPhone);
				updateInfo.put("totOrderAmount", totOrderAmount);
				updateInfo.put("option", option);
				updateInfo.put("shippingFee", shippingFee);
				updateInfo.put("shippingAddr", shippingAddr);
				updateInfo.put("deliveryMsg", deliveryMsg);
				updateInfo.put("orderType", orderType);
				updateInfo.put("productName", productName);
				updateInfo.put("memo", memo);
				oMapper.updateOrderInfo(updateInfo);
			}
			
		// 3. 상품만 수정
		}else {
			updateInfo.put("pCode", newPCode);
			updateInfo.put("pPrice", pPrice);
			pMapper.updateProductionInfo(updateInfo);
		}

	}
	
	// 일별 주문 상세 내역 화면에서 주문에 대한 정보 변경시
	public void updateOrderInfo(HashMap<String, Object> updateProductionInfo) {
		int orderIdx = Integer.parseInt(updateProductionInfo.get("orderIdx").toString());
		char modifiedCheck = oMapper.getOrderInfoByIdx(orderIdx).getModifiedCheck();
		if(modifiedCheck == 'N') {
			oMapper.updateOrderInfo(updateProductionInfo);
			oMapper.updateModifiedCheck(orderIdx);
		}else if(modifiedCheck == 'Y') {
			oMapper.updateOrderMultiple(updateProductionInfo);
		}
	}
	// *****************************************************
	// =========================== 품목 =====================
	// *****************************************************
	
	
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
		List<String> itemName = (List<String>) params.get("itemName");
		List<String> itemQuantity = (List<String>) params.get("itemQuantity");
		for(int i=0; i < itemName.size(); i++) {
			if(itemName.get(i).equals("item01")) {
				newProductInfo.setItem01(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item02")) {
				newProductInfo.setItem02(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item03")) {
				newProductInfo.setItem03(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item04")) {
				newProductInfo.setItem04(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item05")) {
				newProductInfo.setItem05(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item06")) {
				newProductInfo.setItem06(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item07")) {
				newProductInfo.setItem07(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item08")) {
				newProductInfo.setItem08(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item09")) {
				newProductInfo.setItem09(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item10")) {
				newProductInfo.setItem10(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item11")) {
				newProductInfo.setItem11(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item12")) {
				newProductInfo.setItem12(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item13")) {
				newProductInfo.setItem13(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item14")) {
				newProductInfo.setItem14(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item15")) {
				newProductInfo.setItem15(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item16")) {
				newProductInfo.setItem16(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item17")) {
				newProductInfo.setItem17(Integer.parseInt(itemQuantity.get(i)));
			}else if(itemName.get(i).equals("item18")) {
				newProductInfo.setItem18(Integer.parseInt(itemQuantity.get(i)));
			}else {
				newProductInfo.setItem19(Integer.parseInt(itemQuantity.get(i)));
			}
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
	
	
	// 검색조건으로 검색결과 상품정보 가져오기
	public List<SmartStoreProduct> getSearchProductInfo(HashMap<String, String> searchWords){
		return pMapper.getSearchProductInfo(searchWords);
	}
	
	// 등록된 상품명들 가져오기
	public List<HashMap<String, String>> getAllProductName(){
		return pMapper.getAllProductName();
	}
	
	// 모든 포장재 종류 가져오기
	public List<HashMap<String, String>> getAllWrappingType(){
		return pMapper.getAllWrappingType();
	}
	
	// 모든 상품 구분명 가져오기
	public List<HashMap<String, String>> getAllPName(){
		return pMapper.getAllPName();
	}
}
