package project.monthlyMill.dto;

public class MemberAgreementInfo {
	private String memberId;
	private String agreementNum;
	private String mAgreementModifyDate;
	private String mAgreementRegDate;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAgreementNum() {
		return agreementNum;
	}
	public void setAgreementNum(String agreementNum) {
		this.agreementNum = agreementNum;
	}
	public String getmAgreementModifyDate() {
		return mAgreementModifyDate;
	}
	public void setmAgreementModifyDate(String mAgreementModifyDate) {
		this.mAgreementModifyDate = mAgreementModifyDate;
	}
	public String getmAgreementRegDate() {
		return mAgreementRegDate;
	}
	public void setmAgreementRegDate(String mAgreementRegDate) {
		this.mAgreementRegDate = mAgreementRegDate;
	}
	
	@Override
	public String toString() {
		return "MemberAgreementInfo [memberId=" + memberId + ", agreementNum=" + agreementNum
				+ ", mAgreementModifyDate=" + mAgreementModifyDate + ", mAgreementRegDate=" + mAgreementRegDate + "]";
	}
	
}
