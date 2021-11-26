package project.monthlyMill.customer.menu;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.monthlyMill.dto.Product;

@Mapper
public interface MenuMapper {
	
	// 월간방앗간 MENU
	public List<Product> viewAllProduct();
}
