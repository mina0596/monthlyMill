package project.monthlyMill.customer.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer/service")
public class CSController {
	@GetMapping("/list")
	public String getCsList() {
		return "/customer/askPost_read";
	}
}
