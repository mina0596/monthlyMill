package project.monthlyMill.makers.myPage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.dto.MakerAdditionalInfo;
import project.monthlyMill.dto.MakerStore;
import project.monthlyMill.dto.Member;

@Controller
@RequestMapping("/makers/myPage")
public class MakersMyPageController {
	
	private static final Logger log = LoggerFactory.getLogger(MakersMyPageController.class);
	
	@Autowired
	MakersMyPageService makersMyPageService;
	
	@Autowired
	PasswordEncoder pwEncoder;

	
	
	// ******************* 기본정보 ********************
	
	// 메이커스 기본정보 뿌려주기
	@GetMapping("/user/basicInfo")
	public String getUserInfo(HttpSession session, Model model) {
		String makerId = (String) session.getAttribute("SID");
		Member makersInfo = makersMyPageService.getMakersInfoById(makerId);
		model.addAttribute("makersInfo", makersInfo);
		return "/makers/makersMypage_user_read";
	}
	
	// 메이커스 기본정보 수정을 위한 비밀번호 확인 화면 이동
	@GetMapping("/user/confirmPw")
	public String getUserConfirmedPw() {
		
		return "/makers/makersMypage_pwReInput";
	}
	
	// 메이커스 기본정보 수정을 위한 비밀번호 받아와 확인
	@PostMapping("/pwConfirm")
	@ResponseBody
	public HashMap<String, Boolean> getPwConfirmed(String inputPw, HttpSession session){
		HashMap<String, Boolean> result = new HashMap<String, Boolean>();
		String makerId = (String) session.getAttribute("SID");
		boolean check = pwEncoder.matches(inputPw, makersMyPageService.getMakersInfoById(makerId).getMemberPw());
		if(check) {
			result.put("pwCheck", true);
		}else {
			result.put("pwCheck", false);
		}
		return result; 
	}
	
	// 메이커스 기본정보 수정화면
	@GetMapping("/user/editBasicInfo")
	public String getEditVersion(HttpSession session, Model model) {
		String makerId = (String) session.getAttribute("SID");
		Member makersInfo = makersMyPageService.getMakersInfoById(makerId);
		model.addAttribute("makersInfo", makersInfo);
		return "/makers/makersMypage_user_edit";
	}
	
	
	
	
	// ************************ 입점정보 **********************
	
	// 메이커스 입점정보 뿌려주기
	@GetMapping("/storeInfo")
	public String getStoreInfo(HttpSession session, Model model) {
		String makerId = (String) session.getAttribute("SID");
		MakerStore storeInfo = makersMyPageService.getStoreInfoById(makerId);
		model.addAttribute("storeInfo", storeInfo);
		return "/makers/makersMypage_enter_read";
	}
		
	
	// 메이커스 입점정보 수정화면
	@GetMapping("/user/editStoreInfo")
	public String getStoreEditVersion(HttpSession session, Model model) {
		String makerId = (String) session.getAttribute("SID");
		String makerName = (String) session.getAttribute("SNAME");
		
		MakerStore storeInfo = makersMyPageService.getStoreInfoById(makerId);
		model.addAttribute("storeInfo", storeInfo);
		model.addAttribute("makersName", makerName);
		return "/makers/makersMypage_enter_edit";
	}

	// 메이커스 입점정보 수정을 위한 비밀번호 확인 화면 이동
	@GetMapping("/store/confirmPw")
	public String getStoreConfirmedPw() {
		
		return "/makers/makersMypage_pwReInput";
	}
	
	// 입점정보 수정 후 화면이동
	@PostMapping("/user/editStoreInfo")
	public String updateStoreInfo() {
		return "redirect:/makers/myPage/storeInfo";
	}
	
	
	
	
	// ********************** 추가정보 ******************
	// 추가정보가 없을 시 화면
	@GetMapping("/user/additionalInfo")
	public String getAdditionalInfo(HttpSession session, Model model) {
		String makerId = (String) session.getAttribute("SID");
		MakerAdditionalInfo addInfo = makersMyPageService.getAddInfoById(makerId);
		// 메이커스 추가정보가 DB에 없으면 등록할 수 있는 화면으로 이동
		if(addInfo == null) {
			
			return "/makers/makersMypage_additional_info";
		}else {
			// 메이커스 추가정보가 DB에 있으면 정보 조회화면으로 이동
			model.addAttribute("addInfo", addInfo);
			return "/makers/makersMypage_additional_read";
		}
	}
	
	// 추가정보 수정화면
	@GetMapping("/user/editAdditionalInfo")
	public String updateAddInfo() {
		return "/makers/makersMypage_additional_set";
	}
	
	// 추가정보 등록화면
	@GetMapping("/user/regAdditionalInfo")
	public String setAddInfo() {
		return "/makers/makersMypage_additional_set";
	}
	
	// 추가정보 등록 후 화면이동
	@PostMapping("/user/regAdditionalInfo")
	public String finishAddInfo() {
		return "redirect:/makers/myPage/user/addtionalInfo";
	}
	
}
