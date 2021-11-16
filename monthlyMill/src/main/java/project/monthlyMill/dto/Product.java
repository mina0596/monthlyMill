package project.monthlyMill.dto;

import lombok.Data;

@Data
public class Product {
	private String pCode;
	private String pCate;
	private String pName;
	private int pPrice;
	private char discountCheck;
	private int discountAmount;
	private String discountType;
	private String sellStartDate;
	private String sellCompDate;
	private String vatType;
	private int quantityLimit;
	private String thumbName;
	private String thumbPath;
	private long thumbSize;
	private String imgName;
	private String imgPath;
	private String imgSize;
	private String nutrition;
	private String detail;
	private String recipe;
	private String ingredient;
	private String regId;
	private String pModifyDate;
	private String pRegDate;
	private String pPreferAge;
	private String pMainUsage;
	private String pTexture;
	private String pTaste;
	private int defaultShippingFee;
	private String hashTag;
	
		
}
