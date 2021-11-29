package project.monthlyMill.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	
	
	// controller로 보내기 전에 처리함
	// 변환이 false라면 controller로 요청을 안함
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
					throws Exception{
		HttpSession session = request.getSession();
		String smartStoreSesssionId = (String) session.getAttribute("SESSIONID");
		
		// smartStore에서는 login.html 에만 들어갈 수 있게 걸어두기
		String[] smartStoreUri = {"/smartStore/login", "/smartStore/join/idDupCheck"
								 , "/smartStore/join", "/smartStore/join/getMemberInfo"};
		ArrayList<String> exceptUri = new ArrayList<>(Arrays.asList(smartStoreUri));
		
		// 주소값 확인
		String curUriAddr = request.getRequestURI();
		
		
		// session 에 SESSIONID 가 저장되어잇지않으면, 무조건 login 페이지로 이동시키기
		Boolean accessCheck = true;
		if(smartStoreSesssionId == null || smartStoreSesssionId.equals("")) {
			log.info("session에 저장된 아이디 확인하기 :{}", smartStoreSesssionId);
			log.info("URI 값 확인 :{}", curUriAddr);
			log.info("발동하는지 확인");
			
			if(!exceptUri.contains(curUriAddr)) {
				accessCheck = false;
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out;
				out = response.getWriter();
				out.println("<script> alert('권한이 없습니다. 로그인 후에 이용할 수 있습니다.'); location.href='/smartStore/login'; </script>");
				out.flush();
				//response.sendRedirect("/smartStore/login");
			}else {
				accessCheck = true;
			}
		}else {
			accessCheck = true;
			log.info("로그인정보가 들어있을 경우 확인 메세지 :{}", smartStoreSesssionId);
		}
		return accessCheck;
	}
	
	
	// controller의 handler가 끝나면 처리됨
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle URI :{}", request.getRequestURI());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	
	// view까지 처리가 끝난 후에 처리됨
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}



