package project.monthlyMill.dto;

import lombok.Data;

@Data
public class Shipment {
	private int idx;
	private String orderNum;
	private String shippingMethod;
	private int shippingFee;
	private String receiverName; 
	private String receiverPhoneNum; 
	private String receiverHomeNum; 
	private String receiverPostalCode; 
	private String receiverAddr; 
	private String receiverDetailAddr;
	private String expDelDate;
	private String filedDate;
	private String shippedDate;
}
