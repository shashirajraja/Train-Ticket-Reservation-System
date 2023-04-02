package com.shashi.beans;
import java.io.*;
@SuppressWarnings("serial")
public class TransBean implements Serializable{
	private String transId,mailId,fromStn,toStn,time;
	private long seatNo,trainNo, quantity;
	private double amount;
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getFromStn() {
		return fromStn;
	}
	public void setFromStn(String fromStn) {
		this.fromStn = fromStn;
	}
	public String getToStn() {
		return toStn;
	}
	public void setToStn(String toStn) {
		this.toStn = toStn;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(long seatNo) {
		this.seatNo = seatNo;
	}
	public long getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(long trainNo) {
		this.trainNo = trainNo;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
