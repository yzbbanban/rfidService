package com.yzb.rfid.entity;

import java.io.Serializable;
import java.util.Date;

public class UpdateMsg implements Serializable{

	private static final long serialVersionUID = 1289923250366076444L;
	private int id;
	private String version;
	private Date createTime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "UpdateMsg [id=" + id + ", version=" + version + ", createTime="
				+ createTime + "]";
	}
	
}
