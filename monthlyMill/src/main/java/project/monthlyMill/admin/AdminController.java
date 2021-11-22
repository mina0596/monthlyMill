package project.monthlyMill.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/main")
	public String getMainPage() {
		return "/admin/adminMain";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/admin/adminLogin";
	}
	
	// 관리자 계정 추가화면
	@GetMapping("/addAccount")
	public String addAccount() {
		return "/admin/adminAccountSet";
	}
}
