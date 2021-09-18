package project.monthlyMill.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import project.monthlyMill.dto.Member;

@Controller
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private final LoginService loginService;
	
	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@GetMapping("/login")
	public String memberLogin() {
		return "/common/login";
	}
	
	@PostMapping("/login")
	public String memberLogin(@RequestParam(name = "loginType", required = false) int loginType
							, @RequestParam(name = "memberId", required = false) String loginId
							, @RequestParam(name = "memberPw", required = false) String loginPw
							, HttpSession session, HttpServletResponse response, RedirectAttributes reAttr) {
		//로그인할때 권한에 대한 설정 넣어야함
		//비밀번호 암호화 넣어야함
		Member loginWithId = loginService.loginCheck(loginId);
		log.info("로그인정보확인해보자 :{}", loginType);
		if(loginWithId == null || !loginWithId.getMemberPw().equals(loginPw) || loginWithId.getMemberCateNum() != loginType){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('아이디와 비밀번호를 다시 확인해주세요'); location.href='/login';</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(loginWithId.getMemberPw().equals(loginPw) && loginWithId.getMemberCateNum() == loginType) {
			session.setAttribute("SMNUM", loginWithId.getMemberNum());
			session.setAttribute("SID", loginId);
			session.setAttribute("SNAME", loginWithId.getMemberName());
			session.setAttribute("SLEVEL", loginWithId.getMemberCateNum());
			session.setAttribute("STEL", loginWithId.getMemberPhone());
			session.setAttribute("SADDR", loginWithId.getMemberAddr());
			session.setAttribute("SPCODE", loginWithId.getMemberPostalCode());
		}
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String memberlogout(HttpSession session) {
		session.invalidate();
		return "/common/main";
	}
}
