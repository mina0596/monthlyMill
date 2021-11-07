package project.monthlyMill.dto;

import lombok.Data;

@Data
public class Order {
	private int idx;
	private String orderNum;
	private String memberId;
	private String pCode;
	private int pCount;
	private char matchingCheck;
	private String makerId;
	private String matchingNum;
	private char refundCheck;
	private String orderDate;
	private int totalProductPrice;
	private String shippingMethod;
	private int actualShippingFee;
	private int mPaidShippingFee;
	private char discountCheck;
	private int totalDiscountAmount;
	private String discountMethod;
	private String couponNum;
	private char paymentCheck;
	private int paymentAmount;
	private String paymentDate;
	private String orderModifyDate;
	private String orderRegDate;
	private char cancelCheck;
	private String memo;
	
}
