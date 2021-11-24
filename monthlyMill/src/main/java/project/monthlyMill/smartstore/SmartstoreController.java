package project.monthlyMill.smartstore;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.dto.SmartStoreOrder;
import project.monthlyMill.dto.SmartStoreProduct;

@Controller
@RequestMapping("/smartStore")
public class SmartstoreController {
	
	@Autowired
	SmartstoreService ssService;
	
	
	private static final Logger log = LoggerFactory.getLogger(SmartstoreController.class);
	
	// 스마트스토어 메인페이지
	@GetMapping("/main")
	public String mainPage(Model model) {
		List<SmartStoreOrder> allOrderInfo = ssService.getAllOrderInfo();
		model.addAttribute("allOrderInfo", allOrderInfo);
		return "/smartstore/menu1_attachFile";
	}
	
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
