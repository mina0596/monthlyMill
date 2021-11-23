package project.monthlyMill.dto;

import lombok.Data;

@Data
public class SmartStoreOrder {
	private int idx;
	private String shippingDate;
	private String paidDate;
	private String shippingMethod;
	private String ordererName;
	private String orderderPhone;
	private String receiverName;
	private String receiverPhone;
	private String productName;
	private String detailedProduct;
	private String orderQuantity;
	private String totOrderAmount;
	private String option;
	private String shippingAddr;
	private String delieveryMsg;
	private String expDeliveryDate;
	private String regDate;
	private String regMId;
	private String orderType;
	
}
