package project.monthlyMill.makers.sales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 메이커스의 정산에 대한 정보 controller

@Controller
@RequestMapping("/makers/sales")
public class MakersSalesController {
	
	@GetMapping("/list")
	public String getSales() {
		return "/makers/makerSalesSettlement";
	}
	
}
