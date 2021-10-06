package project.monthlyMill.dto;

public class Cart {
	private int cartNum;
	private int memberNum;
	private String pCode;
	private int pAmount;
	private String cartRegDate;
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public int getpAmount() {
		return pAmount;
	}
	public void setpAmount(int pAmount) {
		this.pAmount = pAmount;
	}
	public String getCartRegDate() {
		return cartRegDate;
	}
	public void setCartRegDate(String cartRegDate) {
		this.cartRegDate = cartRegDate;
	}
	@Override
	public String toString() {
		return "Cart [cartNum=" + cartNum + ", memberNum=" + memberNum + ", pCode=" + pCode + ", pAmount=" + pAmount
				+ ", cartRegDate=" + cartRegDate + "]";
	}
	
	
	
}
