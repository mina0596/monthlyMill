package project.monthlyMill.common.signup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SMSSender")
public class SMSSender {
	private static final Logger log = LoggerFactory.getLogger(SMSSender.class);
	
	
	@Autowired
	SignupService signupService;
	
	private static final String SERVICE_URL_AGAIN = "http://www.sms9.co.kr/authSendApi/authSendApi_UTF8.php";
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
	
	public String USER_ID = "ehddnr0819";
	public String AUTH_KEY = "O5Omgib0pFMp5qOEODwbw4Aru3UaAnfP";
	//public String CALL_NUM = "01056716928";
	public String CALL_NUM = "01023496670";
	
	
	
	public boolean sendSms(HashMap<String,Object> paramMap) {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		log.info("Send SMS Again In ...");

		HttpPost httpPost = new HttpPost(SERVICE_URL_AGAIN);

		httpPost.addHeader("Content-Type", CONTENT_TYPE);
	
		String sendMsg = "";

		try {
			List<String> messageList = messageSplit(paramMap.get("contents").toString(), 90);
			
			for (int i = 0; i < messageList.size(); i++) {
				log.info("-----------------------------------------------------------------------------------------");
				log.info("-----------------------------------------------------------------------------------------");
				log.info("-----------------------------------------------------------------------------------------");
				log.info("origin SendMsg : " + messageList.get(i));
				
				
				sendMsg = URLEncoder.encode(messageList.get(i), "UTF-8").replace("+", "%20");
	
				//sendMsg = sendMsg.replaceAll("+", "%20");
	
				List<NameValuePair> postParams = new ArrayList<NameValuePair>();
				postParams.add(new BasicNameValuePair("sUserid", USER_ID));
				postParams.add(new BasicNameValuePair("authKey", AUTH_KEY));
				postParams.add(new BasicNameValuePair("sendMsg", sendMsg));
	
				postParams.add(new BasicNameValuePair("destNum", paramMap.get("phoneNumber").toString()));
				postParams.add(new BasicNameValuePair("callNum", CALL_NUM));
				postParams.add(new BasicNameValuePair("sMode", "Real"));
				postParams.add(new BasicNameValuePair("sType", "SMS"));
				
				log.info("postParams :{}", postParams);
				CloseableHttpResponse httpResponse = null;
				httpPost.setEntity(new UrlEncodedFormEntity(postParams));
				httpResponse = httpClient.execute(httpPost);
				
				int responseCode = httpResponse.getStatusLine().getStatusCode();
			
				log.info("HexStr SendMsg : " + sendMsg);
	
				log.info("Response Code: :" + responseCode);
	
				log.info("-----------------------------------------------------------------------------------------");
				log.info("-----------------------------------------------------------------------------------------");
				log.info("-----------------------------------------------------------------------------------------");
				
				if (responseCode == 200) {
					//문자 내역 저장
					paramMap.put("contents", messageList.get(i));
					log.info("paramMap saveHistory직전 확인:{}", paramMap);
					signupService.saveHistory(paramMap);
				}
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
	
				String inputLine;
				StringBuffer response = new StringBuffer();
	
				while ((inputLine = reader.readLine()) != null) {
					response.append(inputLine);
				}
	
				reader.close();
			}
			
			httpClient.close();
			return true;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return false;
	}
	
	public List<String> messageSplit(String txt, int lenLimit) {
        
		List<String> rawResult = new ArrayList<String>();
 
        // 바이트 체크 (영문 1, 한글 2, 특문 1)
        int en = 0;
        int ko = 0;
        int etc = 0;
 
        char[] txtChar = txt.toCharArray();
        String txtResult = "";
        for (int j = 0; j < txtChar.length; j++) {
            if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
                en++;
            } else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
                ko++;
                ko++;
            } else {
                etc++;
            }
            
            txtResult += txtChar[j];
            
            if ((en + ko + etc) >= (lenLimit - 1) || (j == txtChar.length - 1)) {
            	rawResult.add(txtResult);
            	txtResult = "";
            	en = 0;
                ko = 0;
                etc = 0;
            }
        }
        
        return rawResult;
    }
}

	


