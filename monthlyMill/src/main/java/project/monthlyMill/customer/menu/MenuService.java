package project.monthlyMill.customer.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.monthlyMill.dto.Product;

@Service
public class MenuService {
	
	@Autowired
	MenuMapper mMapper;
	
	// 월간방앗간 MENU
	public List<Product> viewAllProduct(){
		return mMapper.viewAllProduct();
	}
}
