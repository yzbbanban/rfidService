package com.yzb.rfid.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RfidUser implements Serializable {

	private static final long serialVersionUID = -8192940647681396784L;
	private long id;
	private String customer_name;
	private String customer_address;
	private long number;
	private String depot_code;
	private Date create_time;
	private String admin_id;
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public long getNumber() {
		return number;
	}

	public void setNumer(long number) {
		this.number = number;
	}

	public String getDepot_code() {
		return depot_code;
	}

	public void setDepot_code(String depot_code) {
		this.depot_code = depot_code;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	@Override
	public String toString() {
		return "RfidUser [id=" + id + ", customer_name=" + customer_name
				+ ", customer_address=" + customer_address + ", numer=" + number
				+ ", depot_code=" + depot_code + ", create_time=" + create_time
				+ ", admin_id=" + admin_id + "]";
	}

}
