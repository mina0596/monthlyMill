package project.monthlyMill.customer.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/menu")
public class MenuController {
	
	@Autowired
	MenuService mService;
	
	@GetMapping("/list")
	public String getMenuList(Model model) {
		model.addAttribute("menuList", mService.viewAllProduct());
		return "/customer/menuList";
	}
}
