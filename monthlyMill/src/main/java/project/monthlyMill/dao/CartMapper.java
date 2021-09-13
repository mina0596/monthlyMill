package project.monthlyMill.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
	public int addItem(Map<String,Object> cartInfo);
}
