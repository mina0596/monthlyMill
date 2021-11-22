package project.monthlyMill.admin.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import project.monthlyMill.dto.Member;

@Controller
@RequestMapping("/admin/customer")
public class AdminCustomerController {
	
	@Autowired
	AdminCustomerService acService;
	
	@GetMapping("/list")
	public String getCustomerList(Model model) {
		List<Member> allMemberInfo = acService.getAllMemberInfo();
		model.addAttribute("mInfo", allMemberInfo);
		return "/admin/adminCustomer_infoSet";
	}
}
