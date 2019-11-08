package com.shashi.beans;
import java.io.*;
@SuppressWarnings("serial")
public class TransBean implements Serializable{
	private String transId,uName,fromStn,toStn,time;
	private long seatNo,trainNo;
	public void setUName(String uName)
	{
		this.uName = uName;
	}
	public String getUName()
	{
		return uName;
	}
	public void setTranId(String  transId)
	{
		this.transId = transId;
	}
	public void setTrainNo(long trainNo)
	{
		this.trainNo = trainNo;
	}
	public void setFromStn(String  fromStn)
	{
		this.fromStn = fromStn;
	}
	public void setToStn(String toStn)
	{
		this.toStn = toStn;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public void setSeats(long seatNo)
	{
		this.seatNo = seatNo;
	}

	public String getTranId()
	{
		return transId  ;
	}
	public long getTrainNo()
	{
		return trainNo ;
	}
	public String getFromStn()
	{
		return fromStn  ;
	}
	public String getToStn()
	{
		return toStn  ;
	}
	public String getTime()
	{
		return time  ;
	}
	public long getSeats()
	{
		return seatNo ;
	}

}
