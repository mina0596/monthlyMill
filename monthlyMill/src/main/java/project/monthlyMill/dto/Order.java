package project.monthlyMill.dto;

public class Order {
	private int idx;
	private String orderNum;
	private int memberNum;
	private String pCode;
	private int cartNum;
	private int pCount;
	private int pPrice;
	private String orderDate;
	private String expDelDate;
	private char orderPayment;
	private char orderRefund;
	private String orderModifyDate;
	private String orderRegDate;
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	public int getCartNum() {
		return cartNum;
	}
	public void setCartNum(int cartNum) {
		cartNum = cartNum;
	}
	public int getpCount() {
		return pCount;
	}
	public void setpCount(int pCount) {
		this.pCount = pCount;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getExpDelDate() {
		return expDelDate;
	}
	public void setExpDelDate(String expDelDate) {
		this.expDelDate = expDelDate;
	}
	public char getOrderPayment() {
		return orderPayment;
	}
	public void setOrderPayment(char orderPayment) {
		this.orderPayment = orderPayment;
	}
	public char getOrderRefund() {
		return orderRefund;
	}
	public void setOrderRefund(char orderRefund) {
		this.orderRefund = orderRefund;
	}
	public String getOrderModifyDate() {
		return orderModifyDate;
	}
	public void setOrderModifyDate(String orderModifyDate) {
		this.orderModifyDate = orderModifyDate;
	}
	public String getOrderRegDate() {
		return orderRegDate;
	}
	public void setOrderRegDate(String orderRegDate) {
		this.orderRegDate = orderRegDate;
	}
	@Override
	public String toString() {
		return "Order [idx=" + idx + ", orderNum=" + orderNum + ", memberNum=" + memberNum + ", pCode=" + pCode
				+ ", CartNum=" + cartNum + ", pCount=" + pCount + ", pPrice=" + pPrice + ", orderDate=" + orderDate
				+ ", expDelDate=" + expDelDate + ", orderPayment=" + orderPayment + ", orderRefund=" + orderRefund
				+ ", orderModifyDate=" + orderModifyDate + ", orderRegDate=" + orderRegDate + "]";
	}
	
	
}
