package project.monthlyMill.makers.auction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


//메이커스가 보는 모든 경매에 대한 controller

@Controller
@RequestMapping("/makers/auction")
public class MakersAuctionController {
	
	
	@GetMapping("/detail")
	public String getAuctionDetail() {
		return "/makers/makerMatch_detail";
	}
	
	@GetMapping("/list")
	public String getAuctionList() {
		return "/makers/makerMatch_list";
	}
	
}
