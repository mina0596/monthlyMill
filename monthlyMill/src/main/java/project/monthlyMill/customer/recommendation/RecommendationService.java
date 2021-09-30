package project.monthlyMill.customer.recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Product;
import project.monthlyMill.hashtag.HashtagMapper;

@Service
public class RecommendationService {
	private static final Logger log = LoggerFactory.getLogger(RecommendationService.class);
	
	@Autowired
	HashtagMapper hashtagMapper;
	
	@Autowired
	RecommendMapper rcmdMapper;
	
	public RecommendationService(HashtagMapper hashtagMapper, RecommendMapper rcmdMapper) {
		this.hashtagMapper = hashtagMapper;
		this.rcmdMapper = rcmdMapper;
	}
	
	//큰 카테고리 해시태그
	public List<Hashtag> getHashtag() {
		return hashtagMapper.getHashtag();
	}
	
	//선택한 해시태그에 대한 검색 결과
	public List<Product> getRcmdProductInfo(List<Map<String, String>> selectedTagsMap1){
		Map<String,String> keyAndValue= null;
		List<Map<String,String>> pInputInfo = new ArrayList<Map<String,String>>();
		
		for(int i=0; i<selectedTagsMap1.size(); i++) {
			keyAndValue= new HashMap<String,String>();
			String midClassName = selectedTagsMap1.get(i).get("hashtagMidClass");
			String tagNum = selectedTagsMap1.get(i).get("hashtagNum");
			if(midClassName.equals("연령대")) {
				keyAndValue.put("pPreferAge", tagNum);
				
			}else if(midClassName.equals("맛")) {
				keyAndValue.put("pTaste", tagNum);
			}else if(midClassName.equals("구매목적")) {
				keyAndValue.put("pMainUsage", tagNum);
			}
			log.info("service단에서 keyAndValue 확인:{}", keyAndValue);
			pInputInfo.add(keyAndValue);
		}
		log.info("service단에서 pInputInfo 확인:{}", pInputInfo);
		
		return rcmdMapper.getRcmdProductInfo(pInputInfo);
	}
}
