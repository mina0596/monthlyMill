package project.monthlyMill.dto;

public class Member {
	private String memberId;
	private String memberPw;
	private int memberCateNum;
	private String memberName;
	private String memberAge;
	private char memberGender;
	private String memberPhone;
	private char memberIdentityCheck;
	private String memberAllergy;
	private String memberDisease;
	private String businessReg;
	private String businessDoc;
	private String businessWeb;
	private String memberInfoModifyDate;
	private String memberInfoRegDate;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public int getMemberCateNum() {
		return memberCateNum;
	}
	public void setMemberCateNum(int memberCateNum) {
		this.memberCateNum = memberCateNum;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(String memberAge) {
		this.memberAge = memberAge;
	}
	public char getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public char getMemberIdentityCheck() {
		return memberIdentityCheck;
	}
	public void setMemberIdentityCheck(char memberIdentityCheck) {
		this.memberIdentityCheck = memberIdentityCheck;
	}
	public String getMemberAllergy() {
		return memberAllergy;
	}
	public void setMemberAllergy(String memberAllergy) {
		this.memberAllergy = memberAllergy;
	}
	public String getMemberDisease() {
		return memberDisease;
	}
	public void setMemberDisease(String memberDisease) {
		this.memberDisease = memberDisease;
	}
	public String getBusinessReg() {
		return businessReg;
	}
	public void setBusinessReg(String businessReg) {
		this.businessReg = businessReg;
	}
	public String getBusinessDoc() {
		return businessDoc;
	}
	public void setBusinessDoc(String businessDoc) {
		this.businessDoc = businessDoc;
	}
	public String getBusinessWeb() {
		return businessWeb;
	}
	public void setBusinessWeb(String businessWeb) {
		this.businessWeb = businessWeb;
	}
	public String getMemberInfoModifyDate() {
		return memberInfoModifyDate;
	}
	public void setMemberInfoModifyDate(String memberInfoModifyDate) {
		this.memberInfoModifyDate = memberInfoModifyDate;
	}
	public String getMemberInfoRegDate() {
		return memberInfoRegDate;
	}
	public void setMemberInfoRegDate(String memberInfoRegDate) {
		this.memberInfoRegDate = memberInfoRegDate;
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", memberCateNum=" + memberCateNum
				+ ", memberName=" + memberName + ", memberAge=" + memberAge + ", memberGender=" + memberGender
				+ ", memberPhone=" + memberPhone + ", memberIdentityCheck=" + memberIdentityCheck + ", memberAllergy="
				+ memberAllergy + ", memberDisease=" + memberDisease + ", businessReg=" + businessReg + ", businessDoc="
				+ businessDoc + ", businessWeb=" + businessWeb + ", memberInfoModifyDate=" + memberInfoModifyDate
				+ ", memberInfoRegDate=" + memberInfoRegDate + "]";
	}
	
	
	
	
}
