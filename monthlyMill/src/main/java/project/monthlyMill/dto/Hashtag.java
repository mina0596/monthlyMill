package project.monthlyMill.dto;

public class Hashtag {
	private String hashtagNum;
	private String hashtagHighClass;
	private String hashtagMidClass;
	private String hashtagName;
	private String hashtagRegDate;
	public String getHashtagNum() {
		return hashtagNum;
	}
	public void setHashtagNum(String hashtagNum) {
		this.hashtagNum = hashtagNum;
	}
	public String getHashtagHighClass() {
		return hashtagHighClass;
	}
	public void setHashtagHighClass(String hashtagHighClass) {
		this.hashtagHighClass = hashtagHighClass;
	}
	public String getHashtagMidClass() {
		return hashtagMidClass;
	}
	public void setHashtagMidClass(String hashtagMidClass) {
		this.hashtagMidClass = hashtagMidClass;
	}
	public String getHashtagName() {
		return hashtagName;
	}
	public void setHashtagName(String hashtagName) {
		this.hashtagName = hashtagName;
	}
	public String getHashtagRegDate() {
		return hashtagRegDate;
	}
	public void setHashtagRegDate(String hashtagRegDate) {
		this.hashtagRegDate = hashtagRegDate;
	}
	
	@Override
	public String toString() {
		return "Hashtag [hashtagNum=" + hashtagNum + ", hashtagHighClass=" + hashtagHighClass + ", hashtagMidClass="
				+ hashtagMidClass + ", hashtagName=" + hashtagName + ", hashtagRegDate=" + hashtagRegDate + "]";
	}
	
	
	
}
