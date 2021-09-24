package project.monthlyMill.signup;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sms")
public class SMSController {
	private static final Logger log = LoggerFactory.getLogger(SMSController.class);
	
	@PostMapping("/sendMsg")
	@ResponseBody
	public Map<String, Object> sendMessage(@RequestBody Map<String, Object> paramMap, HttpRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//3분 이내 5번 이상 실패했으면 5분 이후에 요청
		int msgCount = 0;
		
		if(msgCount >= 5) {
			resultMap.put("isSmsFull", "true");
			return resultMap;
		}
		
		//인증번호 숫자 6자리 포함 문자 전송이면 (sendType random으로 설정하면 됨)
		if(paramMap.containsValue("random")) {
			Random rdm = new Random();
			String numStr = ""; //난수가 저장될 변수
			String rand = "";
			
			for(int i=0; i<6; i++) {
				//0~9까지 난수 생성
				rand = Integer.toString(rdm.nextInt(10));
				numStr += rand;
			}
			paramMap.put("contents", paramMap.get("contents") + numStr);
			paramMap.put("data", numStr);
		}
		resultMap.put("isSuccess", "true");
		
		return resultMap;
	}
}
