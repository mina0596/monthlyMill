package project.monthlyMill.dto;

public class Product {
	private String pCode;
	private String pName;
	private String pPrice;
	private String pIngredient;
	private String pNutrition;
	private int pRegMNum;
	private String pModifyDate;
	private String pRegDate;
	private String pPreferAge;
	private String pMainUsage;
	private String pTexture;
	private String pTaste;
	
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpPrice() {
		return pPrice;
	}
	public void setpPrice(String pPrice) {
		this.pPrice = pPrice;
	}
	public String getpIngredient() {
		return pIngredient;
	}
	public void setpIngredient(String pIngredient) {
		this.pIngredient = pIngredient;
	}
	public String getpNutrition() {
		return pNutrition;
	}
	public void setpNutrition(String pNutrition) {
		this.pNutrition = pNutrition;
	}
	public int getpRegMNum() {
		return pRegMNum;
	}
	public void setpRegMNum(int pRegMNum) {
		this.pRegMNum = pRegMNum;
	}
	public String getpModifyDate() {
		return pModifyDate;
	}
	public void setpModifyDate(String pModifyDate) {
		this.pModifyDate = pModifyDate;
	}
	public String getpRegDate() {
		return pRegDate;
	}
	public void setpRegDate(String pRegDate) {
		this.pRegDate = pRegDate;
	}
	public String getpPreferAge() {
		return pPreferAge;
	}
	public void setpPreferAge(String pPreferAge) {
		this.pPreferAge = pPreferAge;
	}
	public String getpMainUsage() {
		return pMainUsage;
	}
	public void setpMainUsage(String pMainUsage) {
		this.pMainUsage = pMainUsage;
	}
	public String getpTexture() {
		return pTexture;
	}
	public void setpTexture(String pTexture) {
		this.pTexture = pTexture;
	}
	public String getpTaste() {
		return pTaste;
	}
	public void setpTaste(String pTaste) {
		this.pTaste = pTaste;
	}
	
	@Override
	public String toString() {
		return "Product [pCode=" + pCode + ", pName=" + pName + ", pPrice=" + pPrice + ", pIngredient=" + pIngredient
				+ ", pNutrition=" + pNutrition + ", pRegMNum=" + pRegMNum + ", pModifyDate=" + pModifyDate
				+ ", pRegDate=" + pRegDate + ", pPreferAge=" + pPreferAge + ", pMainUsage=" + pMainUsage + ", pTexture="
				+ pTexture + ", pTaste=" + pTaste + "]";
	}
	
}
