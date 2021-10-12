package project.monthlyMill.admin.productRegister;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
	ProductRegisterService pService;
	
	@GetMapping("/productRegister")
	public String productRegister(Model model) {
		model.addAttribute("pCode", pService.getProductCode());
		return "/admin/adminProduct_register";
	}
	
	@PostMapping("/productRegister/uploadImg")
	@ResponseBody
	public void fileUpload(MultipartFile uploadFile) {
		ZonedDateTime current = ZonedDateTime.now();	// 오늘 날짜 가져오기
		// 폴더 경로 정해주기
		String path = "images/" + current.format(DateTimeFormatter.ofPattern("yyyMMdd"));
		File file = new File(path);
		
		// file이 존재한다면, 
		if(!file.exists()) {
			file.mkdirs();	// 폴더생성
		}
		
		log.info("update ajax post: " + uploadFile);
		log.info("update ajax post: " + uploadFile.getOriginalFilename());
		log.info("update ajax post: " + uploadFile.getSize());
		
		// window를 사용한다면, fix되어진 폴더 경로는 c: 그 이후의 경로를 적어줌.
		
		file = new File( "/ehddnr0819/" + path +  "/" + uploadFile.getOriginalFilename());
		
		
		try {
			// 실제 파일이 업로드 되는 부분
			FileUtils.writeByteArrayToFile(file, uploadFile.getBytes());
			//uploadFile.transferTo(file);
		}catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
