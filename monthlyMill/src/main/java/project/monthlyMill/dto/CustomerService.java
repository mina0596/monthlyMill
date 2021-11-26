package project.monthlyMill.dto;

import lombok.Data;

@Data
public class CustomerService {
	private int idx;
	private String csNum;
	private String csCate;
	private String csTitle;
	private String csContent;
	private String filePath;
	private String fileName;
	private long fileSize;
	private String csPublic;
	private String regMId;
	private String regDate;
	private String respondCheck;
	private String respondMethod;
	private String respondMId;
	private String respondDate;
	
	private Member member;
}
