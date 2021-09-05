package project.monthlyMill.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import project.monthlyMill.dto.Hashtag;
import project.monthlyMill.service.RecommendationService;

@Controller
public class RecommendationController {
	
	private static final Logger log = LoggerFactory.getLogger(RecommendationController.class);
	private final RecommendationService rcmdService;
	
	public RecommendationController(RecommendationService rcmdService) {
		this.rcmdService = rcmdService;
	}
	
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
}
