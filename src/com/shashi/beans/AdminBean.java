package com.shashi.beans;

import java.io.Serializable;

public class AdminBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fName;
	private String lName;
	private String pWord;
	private String addR;
	private String mailId;
	private long phNo;

	public void setPWord(String pWord) {
		this.pWord = pWord;
	}

	public String getPWord() {
		return pWord;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getFName() {
		return fName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getLName() {
		return lName;
	}

	public void setAddr(String addR) {
		this.addR = addR;
	}

	public String getAddr() {
		return addR;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setPhNo(long phNo) {
		this.phNo = phNo;
	}

	public long getPhNo() {
		return phNo;
	}
}
