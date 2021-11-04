package project.monthlyMill.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	
	// ***************** 일반회원 ******************
	@GetMapping("/footer")
	public String footerFragment() {
		return "/component/footer";
	}
	
	@GetMapping("/header")
	public String headerFragment() {
		return "/component/header";
	}
	
	@GetMapping("/orderAside")
	public String orderAsideFragment() {
		return "/component/orderAside";
	}
	
	@GetMapping("/mobile_header")
	public String mobileHeaderFragment() {
		return "/component/mobile_header";
	}
	
	@GetMapping("/mobile_sideMenu")
	public String mobileSideMenuFragment() {
		return "/component/mobile_sideMenu";
	}
	
	@GetMapping("/introduce")
	public String introductPage() {
		return "/common/introduce";
	}
	
	
	// ***************** 메이커스 *******************
	
	@GetMapping("/makerHeader")
	public String makersHeader() {
		return "/component/makerHeader";
	}
	
	@GetMapping("/makerAside")
	public String makerAside() {
		return "/component/makerAside";
	}
}
