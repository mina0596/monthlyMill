package project.monthlyMill.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Hashtag;

@Mapper
public interface HashtagMapper {
	
	public List<Hashtag> getHashtag();

}
