package project.monthlyMill.customer.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.CustomerService;

@Service
public class CSService {
	
	@Autowired
	CSMapper csMapper;
	
	// 1. CS sequence 가져오기
	public String selectCSNum() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String nowDate = format.format(date);
		String newCSNum = "cs" + '_' + csMapper.getCSCount() + nowDate;
		return newCSNum;
	}
	
	// 2. CS 문의글 등록
	public boolean addNewCS(HashMap<String, String> csInfo) {
		CustomerService inputData = new CustomerService();
		inputData.setCsCate(csInfo.get("csCategory"));
		inputData.setCsTitle(csInfo.get("csTitle"));
		inputData.setCsContent(csInfo.get("csContent"));
		inputData.setCsPublic(csInfo.get("csPublic"));
		inputData.setRegMId(csInfo.get("loginMId"));
		inputData.setCsNum(selectCSNum());
		try {
			csMapper.addCS(inputData);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// 3. CS 모든 문의글 가져오기
	public List<CustomerService> getAllCS(){
		return csMapper.getAllCS();
	}
	
	// 4. 각각의 문의글 정보 가져오기
	public CustomerService getCsPostByCsNum(String csNum) {
		return csMapper.getCsPostByCsNum(csNum);
		
	}
	
}
