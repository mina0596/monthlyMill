package project.monthlyMill.dto;

import lombok.Data;

@Data
public class SmartStoreOrder {
	private int idx;
	private String orderNum;
	private String shippingDate;
	private String paidDate;
	private String shippingMethod;
	private String ordererName;
	private String orderderPhone;
	private String receiverName;
	private String receiverPhone;
	private String productName;
	private String productCode;
	private String detailedProduct;
	private int orderQuantity;
	private int totOrderAmount;
	private String option;
	private int shippingFee;
	private String shippingAddr;
	private String deliveryMsg;
	private String expDeliveryDate;
	private String regDate;
	private String regMId;
	private String orderType;
	private String productionComp;
	private String shippingPayCheck;
	private String memo;
	private char modifiedCheck;
	
}
