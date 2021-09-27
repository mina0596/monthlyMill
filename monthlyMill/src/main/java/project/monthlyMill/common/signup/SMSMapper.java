package project.monthlyMill.common.signup;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SMSMapper {
	// 본인인증 문자 기록 저장
	public void saveHistory(HashMap<String, Object> paramMap);
	
	// 3분이내 문자 카운트
	public int selectMsgCount(HashMap<String, Object> phoneNumber);
	
}
