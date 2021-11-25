package project.monthlyMill.smartstore;

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
	public String mainPage(Model model) {
		List<SmartStoreOrder> allOrderInfo = ssService.getAllOrderInfo();
		model.addAttribute("allOrderInfo", allOrderInfo);
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
	
	// ============================================
	// ==================== 주문 =================
	// ============================================
	
	// 수기로 주문 입력 페이지
	@GetMapping("/addOrder/manually")
	public String getManualLine() {
		return "/smartstore/menu1_addManualLine";
	}
	 
	// 통합 주문 페이지
	@GetMapping("/orderList")
	public String getDataTable() {
		return "/smartstore/menu2_dataGatherTable";
	}
	
	// 생산일지 페이지 이동
	@GetMapping("/productionPlan")
	public String getOrderList() { 
		return "/smartstore/menu3_productTable";
	}
	
	// ============================================
	// ==================== 상품 =================
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
	public String getProductList(Model model) {
		model.addAttribute("pList", ssService.getAllProductInfo());
		
		return "/smartstore/menu4_itemTable";
	}
	
	

}
