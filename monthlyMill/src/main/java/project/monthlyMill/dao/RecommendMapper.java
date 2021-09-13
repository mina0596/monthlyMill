package project.monthlyMill.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.monthlyMill.dto.Product;

@Mapper
public interface RecommendMapper {
	
	//선택한 해시태그에 대한 검색 결과
	public List<Product> getRcmdProductInfo(List<Map<String,String>> selectedTagsMap);
}
