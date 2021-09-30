package project.monthlyMill.customer.recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.dto.Product;

@Controller
@RequestMapping("/customer/recommend")
public class RecommendationController {
	
	private static final Logger log = LoggerFactory.getLogger(RecommendationController.class);
	@Autowired
	RecommendationService rcmdService;
	
	//나의 떡 추천 찾기
	@GetMapping("/findItem")
	public String findItem(Model model) {
		
		List<Hashtag> allHashtag = rcmdService.getHashtag();
		List<String> highClassList = new ArrayList<String>();
		List<String> midClassList = new ArrayList<String>();
		
		log.info("mapper에서 데려오는 데이터 확인:{}", allHashtag);
		
		for(int i=0; i<allHashtag.size(); i++) {
			highClassList.add(allHashtag.get(i).getHashtagHighClass());
			midClassList.add(allHashtag.get(i).getHashtagMidClass());
		}
		highClassList = highClassList.stream().distinct().collect(Collectors.toList());
		midClassList = midClassList.stream().distinct().collect(Collectors.toList());
		
		log.info("큰카테고리의 해시태그 확인: {}", highClassList);
		log.info("중간카테고리의 해시태그 확인: {}", midClassList);
		
		model.addAttribute("highClassList", highClassList);
		model.addAttribute("midClassList", midClassList);
		model.addAttribute("hashtagList", allHashtag);
		return "/customer/findItem";
	}
	
	//선택된 태그에 해당되는 상품들 데이터
	@PostMapping("/sendSelectedTags")
	@ResponseBody
	public List<Product> getSelectedTags(@RequestBody Map<String,Object> selectedTags, Model model) {
		List<String> selectedTagsList = (List<String>) selectedTags.get("selectedTagsList");
		List<String> selectedMidClass = (List<String>) selectedTags.get("selectedMidClassList");
		log.info("태그코드 넘어오는거 확인해보자: {}", selectedTagsList);
		log.info("태그 중카테고리값 넘어오는거 확인해보자: {}", selectedMidClass);
		
		List<Map<String,String>> selectedTagsInfo = new ArrayList<Map<String,String>>();
		Map<String,String> selectedTagsMap = null;
		for(int j=0; j<selectedTagsList.size(); j++) {
			selectedTagsMap = new HashMap<String,String>();
			selectedTagsMap.put("hashtagMidClass", selectedMidClass.get(j));
			selectedTagsMap.put("hashtagNum", selectedTagsList.get(j));
			log.info("{}", j, "번쨰 list확인:{}", selectedTagsInfo);
			if(!selectedTagsInfo.contains(selectedTagsMap)) {
				selectedTagsInfo.add(selectedTagsMap);
			}
		}
		
		log.info("키와 값으로 만들어놓은 태그정보 확인해보자: {}", selectedTagsInfo);
		
		List<Product> rcmdResult = rcmdService.getRcmdProductInfo(selectedTagsInfo);
		log.info("service단에서 가녀오는거 확인:{}", rcmdResult);
		model.addAttribute("rcmdResult", rcmdResult);
		return rcmdResult;
	}
	
	
	
}
