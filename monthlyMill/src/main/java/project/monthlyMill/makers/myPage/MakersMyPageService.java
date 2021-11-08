package project.monthlyMill.makers.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.MakerStore;
import project.monthlyMill.dto.Member;

@Service
public class MakersMyPageService {
	
	@Autowired
	MakersMyPageMapper makersMyPageMapper;
	
	// 메이커스의 회원 기본정보 아이디로 가져오기
	public Member getMakersInfoById(String makerId) {
		return makersMyPageMapper.getMakersInfoById(makerId);
	}
	
	// 메이커스의 입점정보 아이디로 가져오기 
	public MakerStore getStoreInfoById(String makerId) {
		return makersMyPageMapper.getStoreInfoById(makerId);
	}
}
