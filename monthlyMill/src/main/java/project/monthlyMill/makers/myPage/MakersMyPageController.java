package project.monthlyMill.makers.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/makers/myPage")
public class MakersMyPageController {
	
	@GetMapping("/user/additionalInfo")
	public String getAdditionalInfo() {
		return "/makers/makersMypage_additional_info";
	}
	
	@GetMapping("/storeInfo")
	public String getStoreInfo() {
		return "/makers/makersMypage_enter_read";
	}
	
	@GetMapping("/user/BasicInfo")
	public String getUserInfo() {
		return "/makers/makersMypage_user_read";
	}
}
