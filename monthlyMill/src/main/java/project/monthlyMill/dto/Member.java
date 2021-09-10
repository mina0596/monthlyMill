package project.monthlyMill.dto;

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
	private String memberPostalCode;
	private String memberPhone;
	private char memberIdentityCheck;
	private String refundAccount;
	private String refundAccountBank;
	private String refundName;
	private String memberInfoModifyDate;
	private String memberInfoRegDate;
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
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
	public int getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}
	public String getMemberBday() {
		return memberBday;
	}
	public void setMemberBday(String memberBday) {
		this.memberBday = memberBday;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public char getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getMemberPostalCode() {
		return memberPostalCode;
	}
	public void setMemberPostalCode(String memberPostalCode) {
		this.memberPostalCode = memberPostalCode;
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
	public String getRefundAccount() {
		return refundAccount;
	}
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
	public String getRefundAccountBank() {
		return refundAccountBank;
	}
	public void setRefundAccountBank(String refundAccountBank) {
		this.refundAccountBank = refundAccountBank;
	}
	public String getRefundName() {
		return refundName;
	}
	public void setRefundName(String refundName) {
		this.refundName = refundName;
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
		return "Member [memberNum=" + memberNum + ", memberId=" + memberId + ", memberPw=" + memberPw
				+ ", memberCateNum=" + memberCateNum + ", memberName=" + memberName + ", memberAge=" + memberAge
				+ ", memberBday=" + memberBday + ", memberEmail=" + memberEmail + ", memberGender=" + memberGender
				+ ", memberAddr=" + memberAddr + ", memberPostalCode=" + memberPostalCode + ", memberPhone="
				+ memberPhone + ", memberIdentityCheck=" + memberIdentityCheck + ", refundAccount=" + refundAccount
				+ ", refundAccountBank=" + refundAccountBank + ", refundName=" + refundName + ", memberInfoModifyDate="
				+ memberInfoModifyDate + ", memberInfoRegDate=" + memberInfoRegDate + "]";
	}
	
}
