package com.yzb.rfid.entity;

import java.io.Serializable;
import java.util.Date;

public class RfidException implements Serializable{

	private static final long serialVersionUID = 7080490088075000797L;
	private int id;
	private String exception;
	private Date exception_createTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Date getException_createTime() {
		return exception_createTime;
	}

	public void setException_createTime(Date exception_createTime) {
		this.exception_createTime = exception_createTime;
	}

	@Override
	public String toString() {
		return "RfidException [id=" + id + ", exception=" + exception
				+ ", exception_createTime=" + exception_createTime + "]";
	}
	
}
