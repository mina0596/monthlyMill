package project.monthlyMill.makers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/makers")
public class MakersController {
	
	private static final Logger log = LoggerFactory.getLogger(MakersController.class);
	
	@Autowired
	MakersService makersService;
	
	
	@GetMapping("/main")
	public String mainPage() {
		return "/makers/makerMain";
	}
	
	@GetMapping("/noticeList")
	public String getNoticeList() {
		return "/makers/makerNotice_list";
	}
	
	@GetMapping("/noticeDetail")
	public String getNoticeDetail() {
		return "/makers/makerNotice_detail";
	}
}
