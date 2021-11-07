package project.monthlyMill.dto;

import lombok.Data;

@Data
public class Cart {
	private int cartNum;
	private String memberId;
	private String pCode;
	private int pAmount;
	private String cartRegDate;
	}
