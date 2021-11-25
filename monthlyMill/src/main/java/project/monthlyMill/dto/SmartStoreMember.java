package project.monthlyMill.dto;

import lombok.Data;

@Data
public class SmartStoreMember {
	private int idx;
	private String mId;
	private String mPw;
	private String mName;
	private String mDep;
	private String mPhone;
	private String mEmail;
	private String regDate;
}
