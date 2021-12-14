package project.monthlyMill.smartstore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.dto.SmartStoreOrder;
import project.monthlyMill.dto.SmartStoreProduct;

@Controller
@RequestMapping("/smartStore")
public class SmartstoreController {
	
	@Autowired
	SmartstoreService ssService;
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	private static final Logger log = LoggerFactory.getLogger(SmartstoreController.class);
	
	// 스마트스토어 메인페이지
	@GetMapping("/main")
	public String mainPage(Model model, HttpSession session) {
		List<SmartStoreOrder> allOrderInfo = ssService.getAllOrderInfo();
		model.addAttribute("allOrderInfo", allOrderInfo);
		log.info("세션에 로그인정보 확인 :{}", session.getAttribute("SESSIONID"));
		return "/smartstore/menu1_attachFile";
	}
	
	
	// ============================================
	// ==================== 회원가입 =================
	// ============================================
	
	// 가입 페이지
	@GetMapping("/join")
	public String joinPage() {
		return "/smartstore/join";
	}
	
	// 아이디 중복체크
	@PostMapping("/join/idDupCheck")
	@ResponseBody
	public boolean idDupCheck(@RequestParam(name = "inputId", required = false)String inputId) {
		log.info("아이디 중복체크에 입력한 아이디 확인하기 :{}", inputId);
		return ssService.idDupCheck(inputId);
	}

	// 회원가입 완료
	@PostMapping("/join/getMemberInfo")
	@ResponseBody
	public String getMemberInfo(@RequestBody HashMap<String, String> joinInfo) {
		log.info("받아온 회원 정보 확인:{}", joinInfo);
		ssService.addMember(joinInfo);
		return "success";
	}
	
	// ============================================
	// ==================== 로그인 =================
	// ============================================
	
	// 로그인 페이지
	@GetMapping("/login")
	public String loginPage() {
		return "/smartstore/login";
	}
	
	// 로그인 정보 확인
	@PostMapping("/getLoginInfo")
	@ResponseBody
	public boolean getLoginInfo(@RequestBody HashMap<String, String> loginInfo, HttpSession session) {
		log.info("로그인 정보 확인해보기 :{}", loginInfo);
		log.info("로그인 성공 확인 :{}", ssService.loginCheck(loginInfo, session));
		return ssService.loginCheck(loginInfo, session);
	}
	// 로그아웃 후 로그인 화면으로 이동
	@GetMapping("/logout")
	public String loggingOut(HttpSession session) {
		session.invalidate();
		log.info("로그인 세션 종료");
		return "redirect:/smartStore/login";
	}
	
	
	// ============================================
	// ==================== 주문 ===================
	// ============================================
	
	// 수기로 주문 입력 페이지
	@GetMapping("/addOrder/manually")
	public String getManualLine(Model model) {
		model.addAttribute("newOrderCode", ssService.getNewOrderNum());
		model.addAttribute("pNameList", ssService.getAllProductName());
		return "/smartstore/menu1_addManualLine";
	}
	
	// 주문 등록 처리
	@PostMapping("/addOrder/manually")
	@ResponseBody
	public boolean addNewOrder(@RequestBody HashMap<String, Object> orderInfo, HttpSession session) {
		log.info("새로 등록하는 주문정보 들어오는 값 확인해보자 :{}", orderInfo);
		ssService.addNewOrder(orderInfo, session);
		return true;
	}
	
	
	 
	// 통합 주문 페이지
	@GetMapping("/orderList")
	public String getDataTable(Model model) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(date);
		model.addAttribute("orderList", ssService.getOrderInfoByDate(nowDate));
		model.addAttribute("currentDate", nowDate);
		return "/smartstore/menu2_dataGatherTable";
	}
	
	// 날짜별로 주문 정보 가져오기
	@PostMapping("/getOrderInfoByDeliveryDate")
	@ResponseBody
	public List<SmartStoreOrder> getOrderInfoByDate(@RequestParam(name =  "expDeliveryDate", required = false) String expDate){
		return ssService.getOrderInfoByDate(expDate);
	}
	
	// 생산일지 페이지 이동
	@GetMapping("/productionPlan")
	public String getOrderList(Model model) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(date);
		model.addAttribute("infoList", ssService.getProductionInfo(nowDate));
		model.addAttribute("itemTotal", ssService.getItemsTotal(nowDate));
		model.addAttribute("currentDate", nowDate);
		return "/smartstore/menu3_productJournal";
	}
	
	// 생산일지 날짜 변환시 날짜별로 생산일지 정보 가져오기
	@PostMapping("/getProductionInfoByDate")
	@ResponseBody
	public List<HashMap<String, Object>> sendPruductionInfo(@RequestParam(name = "productionDate", required = false) String productionDate){
		log.info("생산일지 날짜 이동시에 날짜 텍스트 : {}", productionDate);
		List<HashMap<String, Object>> result = new ArrayList<HashMap<String,Object>>();
		result = ssService.getProductionInfo(productionDate);
		// result.add(ssService.getItemsTotal(productionDate));
		return result;
	}
	
	
	// 생산일지 날짜 변환시 날짜별로 통합 품목수 가져오기
	@PostMapping("/getTotalItemByDate")
	@ResponseBody
	public HashMap<String, Integer> sendItemInfo(@RequestParam(name = "productionDate", required = false) String productionDate){
		log.info("생산일지 날짜 이동시에 날짜 텍스트 : {}", productionDate);
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		result = ssService.getItemsTotal(productionDate);
		return result;
	}
	
	
	
	
	// ============================================
	// ==================== 상품 ===================
	// ============================================
	
	// 상품 등록 페이지 이동
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		model.addAttribute("pCode", ssService.getNewPcode());
		
		return "/smartstore/menu4_addItem";
	}
	
	// 상품 등록 처리
	@PostMapping("/getNewProductInfo")
	@ResponseBody
	public String getNewProductInfo(@RequestBody HashMap<String, Object> params) {
		log.info("새로운 품목 정보 ajax받아오는거 확인: {}", params);
		return ssService.getNewProductInfo(params);
	}
	
	
	// 상품표 리스트
	@GetMapping("/productList")
	public String getProductList(Model model
								,@RequestParam(name = "searchKey", required = false) String searchKey
								,@RequestParam(name = "searchValue", required = false) String searchValue) {
		
		log.info("============검색어 받아오는지확인================");
		log.info("검색조건 :{}", searchKey);
		log.info("검색어 :{}", searchValue);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchValue", searchValue);
		paramMap.put("searchKey", searchKey);
		ssService.getSearchProductInfo(paramMap);
		model.addAttribute("pList", ssService.getSearchProductInfo(paramMap));
		
		return "/smartstore/menu4_itemTable";
	}
	
	

}
