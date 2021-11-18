package project.monthlyMill.customer.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/menu")
public class MenuController {
	
	@GetMapping("/list")
	public String getMenuList() {
		return "/customer/menuList";
	}
}
