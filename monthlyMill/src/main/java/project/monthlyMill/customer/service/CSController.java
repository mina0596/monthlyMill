package project.monthlyMill.customer.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer/service")
public class CSController {
	
	@Autowired
	CSService csService;
	
	private static final Logger log = LoggerFactory.getLogger(CSController.class);

	
	// ====== 고객센터 (문의글 리스트) ======
	@GetMapping("/list")
	public String getCsList(Model model) {
		model.addAttribute("csList", csService.getAllCS());
		return "/customer/askPost_list";
	}
	
	// ====== 문의글 쓰기 =======
	@GetMapping("/write")
	public String writeCS(HttpSession session, HttpServletResponse response) throws Exception {
		String loginId = (String) session.getAttribute("SID");
		if(loginId == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.println("<script> alert('로그인 후에 이용할 수 있습니다.'); location.href='/login'; </script>");
			out.flush();
			return "redirect:/login";
		}else {
			return "/customer/askPost_write";
		}
	}
	
	// ====== 작성한 문의글 처리 ======
	@PostMapping("/write")
	@ResponseBody
	public boolean getWrittenPost(@RequestBody HashMap<String, String> params) {
		log.info("문의글 내용 가져오는거 확인:{} ", params);
		return csService.addNewCS(params);
	}
	
	// ====== 각각의 문의글 보기 ======
	@GetMapping("/read")
	public String readIndividual(String csNum, Model model) {
		model.addAttribute("csInfo", csService.getCsPostByCsNum(csNum));
		return "/customer/askPost_read";
	}
}
