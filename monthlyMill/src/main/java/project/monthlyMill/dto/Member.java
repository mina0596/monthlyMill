package project.monthlyMill.dto;

import lombok.Data;

@Data
public class Member {
	private int memberNum;
	private String memberId;
	private String memberPw;
	private int memberCateNum;
	private String memberName;
	private int memberAge;
	private String memberBday;
	private String memberEmail;
	private char memberGender;
	private String memberAddr;
	private String memberDetailAddr;
	private String memberPostalCode;
	private String memberPhone;
	private char memberIdentityCheck;
	private String memberInfoModifyDate;
	private String memberInfoRegDate;
	private String bankName;
	private String bankAccountNum;
	private String holderName;
	private char infoOfferAgree;
	private char outsourceAgree;
	private char newsAgree;
	private String memo;
	
		
}