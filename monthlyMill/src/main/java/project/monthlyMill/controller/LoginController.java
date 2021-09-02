package project.monthlyMill.controller;

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
import project.monthlyMill.service.LoginService;

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
		return "/login";
	}
	
	@PostMapping("/login")
	public String memberLogin(@RequestParam(name = "member", required = false) String memberCate
							, @RequestParam(name = "makers", required = false) String makersCate
							, @RequestParam(name = "memberId", required = false) String loginId
							, @RequestParam(name = "memberPw", required = false) String loginPw
							, HttpSession session, HttpServletResponse response, RedirectAttributes reAttr) {
		//로그인할때 권한에 대한 설정 넣어야함
		//비밀번호 암호화 넣어야함
		int loginMemberCate = 1;
		Member loginWithId = loginService.loginCheck(loginId);
		if(loginWithId == null || !loginWithId.getMemberPw().equals(loginPw) || loginWithId.getMemberCateNum() != loginMemberCate){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("<script>alert('아이디와 비밀번호를 다시 확인해주세요'); location.href='/login';</script>");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(loginWithId.getMemberPw().equals(loginPw) && loginWithId.getMemberCateNum() == loginMemberCate) {
			session.setAttribute("SID", loginId);
			session.setAttribute("SNAME", loginWithId.getMemberName());
			session.setAttribute("SLEVEL", loginWithId.getMemberCateNum());
		}
		return "/main";
	}
}
