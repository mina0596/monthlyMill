package project.monthlyMill.smartstore;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.monthlyMill.dto.SmartStoreOrder;

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
	
	@GetMapping("/addOrder/manually")
	public String getManualLine() {
		return "/smartstore/menu1_addManualLine";
	}
	
	@GetMapping("/dataTable")
	public String getDataTable() {
		return "/smartstore/menu2_dataGatherTable";
	}
	
	@GetMapping("/orderList")
	public String getOrderList() { 
		return "/smartstore/menu3_productTable";
	}
	
	
	// 상품 등록
	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		
		return "/smartstore/menu4_addItem";
	}
	
	
	// 상품표 리스트
	@GetMapping("/productList")
	public String getProductList() { 
		return "/smartstore/menu4_itemTable";
	}

}
