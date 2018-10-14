package com.yzb.rfid.entity;

import java.util.Date;

public class User{

	private long id;
	private String user_name;
	private String password;
	private String salt;
	private String email;
	private int depots;
	private Date create_time;
	private int status;
	private String remark;
	private int is_jb;
	private String pid;
	private String user_type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDepots() {
		return depots;
	}

	public void setDepots(int depots) {
		this.depots = depots;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getIs_jb() {
		return is_jb;
	}

	public void setIs_jb(int is_jb) {
		this.is_jb = is_jb;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", password=" + password + ", salt=" + salt + ", email="
				+ email + ", depots=" + depots + ", create_time=" + create_time + ", status=" + status + ", remark="
				+ remark + ", is_jb=" + is_jb + ", pid=" + pid + ", user_type=" + user_type + "]";
	}

	

}
