package com.shashi.beans;
import java.io.*;
import java.sql.Blob;
@SuppressWarnings("serial")
public class UserBean implements Serializable{
	private String uName,pWord,fName,lName,addR,mailId;
	private long phNo;
	private Blob photo;
	public UserBean() {}
	public void setUName(String uName)
	{
		this.uName=uName;
	}
	public String getUName()
	{
		return uName;
	}
	public void setPWord(String pWord)
	{
		this.pWord = pWord;
	}
	public String getPWord()
	{
		return pWord;
	}
	public void setFName(String fName)
	{
		this.fName = fName;
	}
	public String getFName() {
		return fName;
	}
	public void setLName(String lName)
	{
		this.lName = lName;
	}
	public String getLName()
	{
		return lName;
	}
	public void setAddr(String addR)
	{
		this.addR = addR;
	}
	public String getAddr()
	{
		return addR;
	}
	public void setMailId(String mailId)
	{
		this.mailId = mailId;
	}
	public String getMailId()
	{
		return mailId;
	}
	public void setPhNo(long phNo)
	{
		this.phNo = phNo;
	}
	public long getPhNo()
	{
		return phNo;
	}
	public void setPhoto(Blob photo)
	{
		this.photo = photo;
	}
	public Blob getPhoto()
	{
		return photo;
	}
	
}
