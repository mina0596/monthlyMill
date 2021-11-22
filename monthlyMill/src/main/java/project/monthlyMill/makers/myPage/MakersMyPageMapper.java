package project.monthlyMill.makers.myPage;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.MakerAdditionalInfo;
import project.monthlyMill.dto.MakerStore;
import project.monthlyMill.dto.Member;

@Mapper
public interface MakersMyPageMapper {
	
	// 메이커스의 회원 기본정보 아이디로 가져오기
	public Member getMakersInfoById(String makerId);
	
	// 메이커스의 입점정보 아이디로 가져오기 
	public MakerStore getStoreInfoById(String makerId);
	
	// 메이커스 추가정보 아이디로 가져오기
	public MakerAdditionalInfo getAddInfoById(String makerId);
}
