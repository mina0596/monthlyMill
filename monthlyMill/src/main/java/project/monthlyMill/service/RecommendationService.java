package project.monthlyMill.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import project.monthlyMill.dao.HashtagMapper;
import project.monthlyMill.dao.RecommendMapper;
import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Product;

@Service
public class RecommendationService {
	private final HashtagMapper hashtagMapper;
	private final RecommendMapper rcmdMapper;
	
	public RecommendationService(HashtagMapper hashtagMapper, RecommendMapper rcmdMapper) {
		this.hashtagMapper = hashtagMapper;
		this.rcmdMapper = rcmdMapper;
	}
	
	//큰 카테고리 해시태그
	public List<Hashtag> getHashtag() {
		return hashtagMapper.getHashtag();
	}
	
	//선택한 해시태그에 대한 검색 결과
	public List<Product> getRcmdProductInfo(List<Map<String, String>> selectedTagsMap){
		Map<String,String> keyAndValue= null;
		List<Map<String,String>> pInputInfo = new ArrayList<Map<String,String>>();
		
		for(int i=0; i<selectedTagsMap.size(); i++) {
			keyAndValue= new HashMap<String,String>();
			
			if(selectedTagsMap.get(i).get("hashtagMidClass").equals("연령대")) {
				keyAndValue.put("pPreferAge", selectedTagsMap.get(i).get("hashtagMidClass"));
				pInputInfo.add(keyAndValue);
			}
		}
		rcmdMapper.getRcmdProductInfo(pInputInfo);
		return rcmdMapper.getRcmdProductInfo(pInputInfo);
	}
}
