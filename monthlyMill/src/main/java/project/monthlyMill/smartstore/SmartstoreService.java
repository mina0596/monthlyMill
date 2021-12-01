package project.monthlyMill.smartstore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
		orderInfo.setOrderNum(newOrderInfo.get("orderInfo").toString());
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
			orderInfo.setDelieveryMsg(newOrderInfo.get("delieveryMsg").toString());
			orderInfo.setShippingFee(Integer.parseInt(newOrderInfo.get("shippingFee").toString()));
			orderInfo.setOrderType(newOrderInfo.get("orderType").toString());
			orderInfo.setExpDeliveryDate(newOrderInfo.get("expDeliveryDate").toString());
		}else {
			for(int i=0; i < orderedProductNum; i++) {
				
			}
		}
		oMapper.addOrder(orderInfo);
	}
	
	// 생산일지 정보 가져오기
	public List<HashMap<String, Object>> getProductionInfo(String expDate){
		return oMapper.getProductionInfo(expDate);
	}
	
	// 생산일지에서 날짜별 생산해야할 각각의 품목 수
	public HashMap<String, Integer> getItemsTotal(String expDate){
		return oMapper.getItemsTotal(expDate);
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
	
	
	// 검색조건으로 검색결과 상품정보 가져오기
	public List<SmartStoreProduct> getSearchProductInfo(HashMap<String, String> searchWords){
		return pMapper.getSearchProductInfo(searchWords);
	}
	
	// 등록된 상품명들 가져오기
	public List<HashMap<String, String>> getAllProductName(){
		return pMapper.getAllProductName();
	}
}
