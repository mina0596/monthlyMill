package project.monthlyMill.dto;

public class RefundAccount {
	private int memberNum;
	private String bankName;
	private String bankAccountNum;
	private String holderName;
	
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccountNum() {
		return bankAccountNum;
	}
	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	@Override
	public String toString() {
		return "RefundAccount [memberNum=" + memberNum + ", bankName=" + bankName + ", bankAccountNum=" + bankAccountNum
				+ ", holderName=" + holderName + "]";
	}
	
	
	
}
