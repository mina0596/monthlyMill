package project.monthlyMill.signup;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Member;
import project.monthlyMill.dto.RefundAccount;
import project.monthlyMill.hashtag.HashtagMapper;

@Service
public class SignupService {

	//의존성 주입
	private final SignupMapper memberMapper;
	private final HashtagMapper tagMapper;
	
	@Autowired
	public SignupService(SignupMapper memberMapper, HashtagMapper tagMapper) {
		this.memberMapper = memberMapper;
		this.tagMapper = tagMapper;
	}
	
	//==================================서비스 로직 시작=============================
	
	// 1.회원가입시 아이디 중복체크
	public boolean getMemberInfoById(String inputId) {
		Member memberInfoByIdResult = memberMapper.getMemberInfoById(inputId);
		if(memberInfoByIdResult != null) {
			return false;
		}else{
			return true;
		}
	}
	
	// 2.회원가입시 태그들
	public List<Hashtag> getTagNames() {
		return tagMapper.getHashtag();
	}
	
	// 3.회원가입시 기본정보 등록하기
	public void addBasicMembInfo(Member mBasicInfo) {
		memberMapper.addBasicMembInfo(mBasicInfo);
	}
	
	// 4.회원가입시 환불정보 등록하기
	public void addRefundInfo(RefundAccount mRefundInfo) {
		memberMapper.addRefundInfo(mRefundInfo);
	}
	
	
}
