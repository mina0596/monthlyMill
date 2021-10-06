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
	private char paymentCheck;
	private char refundCheck;
	private String orderModifyDate;
	private String orderRegDate;
	private String receiverName; 
	private String receiverPhoneNum; 
	private String receiverHomeNum; 
	private String receiverPostalCode; 
	private String receiverAddr; 
	private String receiverDetailAddr;
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
		this.cartNum = cartNum;
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
	public char getPaymentCheck() {
		return paymentCheck;
	}
	public void setPaymentCheck(char paymentCheck) {
		this.paymentCheck = paymentCheck;
	}
	public char getRefundCheck() {
		return refundCheck;
	}
	public void setRefundCheck(char refundCheck) {
		this.refundCheck = refundCheck;
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
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhoneNum() {
		return receiverPhoneNum;
	}
	public void setReceiverPhoneNum(String receiverPhoneNum) {
		this.receiverPhoneNum = receiverPhoneNum;
	}
	public String getReceiverHomeNum() {
		return receiverHomeNum;
	}
	public void setReceiverHomeNum(String receiverHomeNum) {
		this.receiverHomeNum = receiverHomeNum;
	}
	public String getReceiverPostalCode() {
		return receiverPostalCode;
	}
	public void setReceiverPostalCode(String receiverPostalCode) {
		this.receiverPostalCode = receiverPostalCode;
	}
	public String getReceiverAddr() {
		return receiverAddr;
	}
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
	public String getReceiverDetailAddr() {
		return receiverDetailAddr;
	}
	public void setReceiverDetailAddr(String receiverDetailAddr) {
		this.receiverDetailAddr = receiverDetailAddr;
	}
	@Override
	public String toString() {
		return "Order [idx=" + idx + ", orderNum=" + orderNum + ", memberNum=" + memberNum + ", pCode=" + pCode
				+ ", cartNum=" + cartNum + ", pCount=" + pCount + ", pPrice=" + pPrice + ", orderDate=" + orderDate
				+ ", expDelDate=" + expDelDate + ", paymentCheck=" + paymentCheck + ", refundCheck=" + refundCheck
				+ ", orderModifyDate=" + orderModifyDate + ", orderRegDate=" + orderRegDate + ", receiverName="
				+ receiverName + ", receiverPhoneNum=" + receiverPhoneNum + ", receiverHomeNum=" + receiverHomeNum
				+ ", receiverPostalCode=" + receiverPostalCode + ", receiverAddr=" + receiverAddr
				+ ", receiverDetailAddr=" + receiverDetailAddr + "]";
	} 
	
		
}
