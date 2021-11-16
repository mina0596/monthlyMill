package project.monthlyMill.admin.productRegister;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.monthlyMill.dto.Product;

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
	
	
	
	// ******** 대표이미지 파일 정보 가져오기 **********
	@PostMapping("/productRegister/getProductInfo")
	@ResponseBody
	public String getPInfo(@RequestBody HashMap<String, Object> params, HttpSession session) {
		log.info("들어오는 상품정보 확인해보기 : {}", params);
		pService.getProductBasicInfo(params, session);
		return "success";
	}
	
	
	// ******** 대표이미지 파일 정보 가져오기 **********
	@PostMapping("/productRegister/thumbnail")
	@ResponseBody
	public void fileUpload(MultipartFile thumbnailData, String pCode) {
		pService.getThumbnailInfo(thumbnailData, pCode);
		log.info("pCode는 ajax에서 가져오나 확인:{}", pCode);
	}
	
	
}
