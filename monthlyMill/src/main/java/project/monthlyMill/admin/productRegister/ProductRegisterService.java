package project.monthlyMill.admin.productRegister;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import project.monthlyMill.dto.Product;

@Service
public class ProductRegisterService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductRegisterService.class);

	
	@Autowired
	ProductMapper productMapper;
	
	// 1. 상품 등록 하기
		// 1.1 상품 기본정보 입력
		public void getProductBasicInfo(HashMap<String, Object> params, HttpSession session) {
			// ajax로 가져온 상품정보 Product 객체에 넣어주기
			String regId = (String) session.getAttribute("SID");
			Product pInfo = new Product();
			pInfo.setPCode(params.get("pCode").toString());
			pInfo.setPName(params.get("pName").toString());
			pInfo.setPPrice(Integer.parseInt(params.get("pPrice").toString()));
			pInfo.setDiscountAmount(Integer.parseInt(params.get("discountAmount").toString()));
			pInfo.setDiscountType(params.get("discountType").toString());
			pInfo.setSellStartDate(params.get("sellStartDate").toString());
			pInfo.setSellCompDate(params.get("sellCompDate").toString());
			pInfo.setVatType(params.get("vatType").toString());
			pInfo.setQuantityLimit(Integer.parseInt(params.get("quantityLimit").toString()));
			pInfo.setDetail(params.get("detail").toString());
			pInfo.setNutrition(params.get("nutrition").toString());
			pInfo.setRecipe(params.get("recipe").toString());
			pInfo.setDefaultShippingFee(Integer.parseInt(params.get("defaultShippingFee").toString()));
			pInfo.setRegId(regId);
			productMapper.addProduct(pInfo);
		}
	
		// 1.2 상품 대표 이미지 정보 입력
		
		public void getThumbnailInfo(MultipartFile thumbnailData, String pCode) {
			ZonedDateTime current = ZonedDateTime.now();	// 오늘 날짜 가져오기
			// 폴더 경로 정해주기
			String path = "/img/" + "thumbnail";
			String contentType = thumbnailData.getContentType();
			String orgFileExtention, newFileName;
			File file = new File(path);
			
			log.info("update ajax post: " + thumbnailData);
			log.info("update ajax post: " + thumbnailData.getOriginalFilename());
			log.info("update ajax post: " + thumbnailData.getSize());
			log.info("update ajax post: " + thumbnailData.getContentType());
			
			if(contentType.contains("image/jpeg")) {
				orgFileExtention = ".jpg";
			}else if(contentType.contains("image/png")) {
				orgFileExtention = ".png";
			}else if(contentType.contains("image/gif")) {
				orgFileExtention = ".gif";
			}else {
				orgFileExtention = "";
			}
			
			newFileName = pCode + "_" + current.format(DateTimeFormatter.ofPattern("yyyMMdd")) + orgFileExtention;
			// window를 사용한다면, fix되어진 폴더 경로는 c: 그 이후의 경로를 적어줌.
			
			// Product 객체에 썸네일 파일 정보 입력 후 update쿼리 실행
			Product pInfo = new Product();
			pInfo.setThumbName(thumbnailData.getOriginalFilename());
			pInfo.setThumbPath(path + "/" + newFileName);
			pInfo.setThumbSize(thumbnailData.getSize());
			pInfo.setPCode(pCode);
			productMapper.updatePInfoByPCode(pInfo);
			
			// 업로드된 파일을 새로운 이름으로 바꾸어 지정된 경로에 저장
			file = new File( contentType + path +  "/" + newFileName);
			// file이 존재한다면, 
			if(!file.exists()) {
				file.mkdirs();	// 폴더생성
			}
			try {
				// 실제 파일이 업로드 되는 부분
				FileUtils.writeByteArrayToFile(file, thumbnailData.getBytes());
				thumbnailData.transferTo(file);
				log.info("파일 업로드 성공함");
			}catch (Exception e) {
				log.error(e.getMessage());
			}
			
			
		}
		
	
	// 2. 새로운 상품 코드 가져오기
	public String getProductCode() {
		return productMapper.getProductCode();
	}
	
}
