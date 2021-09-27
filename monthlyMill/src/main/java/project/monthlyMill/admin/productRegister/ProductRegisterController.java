package project.monthlyMill.admin.productRegister;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class ProductRegisterController {
	
	private static final Logger log = LoggerFactory.getLogger(ProductRegisterController.class);
	
	@GetMapping("/productRegister")
	public String productRegister() {
		return "/admin/adminProduct_register";
	}
	
	@PostMapping("/productRegister/uploadImg")
	@ResponseBody
	public void fileUpload(MultipartFile uploadFile) {
		ZonedDateTime current = ZonedDateTime.now();
		String path = "images/" + current.format(DateTimeFormatter.ofPattern("yyyMMdd"));
		File file = new File(path);
		
		log.info("update ajax post" + uploadFile.getOriginalFilename());
		log.info("update ajax post" + uploadFile.getSize());
		
		file = new File(path + "/" + uploadFile.getOriginalFilename());
		try {
			uploadFile.transferTo(file);
		}catch (Exception e) {
			log.error(e.getMessage());
		} 
	}
}
