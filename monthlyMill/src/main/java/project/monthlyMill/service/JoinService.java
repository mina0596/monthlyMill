package project.monthlyMill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dao.HashtagMapper;
import project.monthlyMill.dao.MemberMapper;
import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Member;

@Service
public class JoinService {

	//의존성 주입
	private final MemberMapper memberMapper;
	private final HashtagMapper tagMapper;
	
	@Autowired
	public JoinService(MemberMapper memberMapper, HashtagMapper tagMapper) {
		this.memberMapper = memberMapper;
		this.tagMapper = tagMapper;
	}
	
	//==================================서비스 로직 시작=============================
	public boolean getMemberInfoById(String inputId) {
		Member memberInfoByIdResult = memberMapper.getMemberInfoById(inputId);
		if(memberInfoByIdResult != null) {
			return false;
		}else{
			return true;
		}
	}
	
	public List<Hashtag> getTagNames() {
		return tagMapper.getHashtag();
	}
	
}
