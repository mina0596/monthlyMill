package project.monthlyMill.dto;

import lombok.Data;

@Data
public class MakerAdditionalInfo {
	private int idx;
	private String makerId;
	private String managerName;
	private String managerEmail;
	private String managerPhone;
	private String managerFax;
	private String bankName;
	private String bankHolderName;	
	private String bankAccountNum;
	private String shippingBrandName;
	private String shippingAddr;
	private String shippingPhone;
	private String returnBrandName;
	private String returnAddr;
	private String returnPhone;
	
}
