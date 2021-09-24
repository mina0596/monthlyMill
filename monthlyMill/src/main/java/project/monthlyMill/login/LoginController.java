package project.monthlyMill.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.monthlyMill.dto.Member;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private final LoginService loginService;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public LoginController(LoginService loginService, PasswordEncoder passwordEncoder) {
		this.loginService = loginService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping("/login")
	public String memberLogin() {
		return "/common/login";
	}
	
	@PostMapping("/login/getInfo")
	@ResponseBody
	public Boolean memberLogin(@RequestBody Map<String,String> loginInfoMap, HttpSession session, HttpServletResponse response) {
		//로그인할때 권한에 대한 설정 넣어야함
		//비밀번호 암호화 넣어야함
		Member loginWithId = loginService.loginCheck(loginInfoMap.get("inputId"));
		log.info("로그인정보확인해보자 :{}", loginWithId);
		boolean check = passwordEncoder.matches(loginInfoMap.get("inputPw"), loginWithId.getMemberPw());
		if(loginWithId == null || !passwordEncoder.matches(loginInfoMap.get("inputPw"), loginWithId.getMemberPw()) || loginWithId.getMemberCateNum() != Integer.parseInt(loginInfoMap.get("inputMType"))){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('아이디와 비밀번호를 다시 확인해주세요'); location.href='/login';</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(check && loginWithId.getMemberCateNum() == Integer.parseInt(loginInfoMap.get("inputMType"))) {
			loginService.saveInSession(loginWithId, session);
		}
		return true;
	}
	
	@PostMapping("/login")
	public String loginDone() {
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String memberlogout(HttpSession session) {
		session.invalidate();
		return "/common/main";
	}
}
