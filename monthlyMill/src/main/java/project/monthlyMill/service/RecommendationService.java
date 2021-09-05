package project.monthlyMill.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import project.monthlyMill.dao.HashtagMapper;
import project.monthlyMill.dto.Hashtag;

@Service
public class RecommendationService {
	private final HashtagMapper hashtagMapper;
	
	public RecommendationService(HashtagMapper hashtagMapper) {
		this.hashtagMapper = hashtagMapper;
	}
	
	//큰 카테고리 해시태그
	public List<Hashtag> getHashtag() {
		return hashtagMapper.getHashtag();
	}
}
