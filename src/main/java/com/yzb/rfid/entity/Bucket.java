package com.yzb.rfid.entity;

import java.io.Serializable;
import java.util.Date;

public class Bucket implements Serializable {

    private static final long serialVersionUID = 7853887896697348381L;
    private long id;
    private String bucket_code;// 吨桶编号【唯一，扫描进来】

    private long manufactor_id;// 厂家id
    private String product_code;//产品编号
    private int customer_id;//客户id

    private int bucket_address;// 0回收，1，产品入库，2在客户那，3在途
    private int status;// 0表示报废 1.表示正常
    private String depot_code;// 创建公司编号
    private Date create_time;// 创建时间
    private boolean checked;
    private long admin_id;// 创建人
    private long idTime;// 读取次数
    
    private int outInStatus;//1入库、0出库

    private int dataIsRight;//0：桶的绑定产品、客户正常 1：异常
    private int record_count;//周转回收次数


    public int getOutInStatus() {
        return outInStatus;
    }

    public void setOutInStatus(int outInStatus) {
        this.outInStatus = outInStatus;
    }
    
    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public long getIdTime() {
        return idTime;
    }

    public void setIdTime(long idTime) {
        this.idTime = idTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBucket_code() {
        return bucket_code;
    }

    public void setBucket_code(String bucket_code) {
        this.bucket_code = bucket_code;
    }

    public long getManufactor_id() {
        return manufactor_id;
    }

    public void setManufactor_id(long manufactor_id) {
        this.manufactor_id = manufactor_id;
    }

    public int getBucket_address() {
        return bucket_address;
    }

    public void setBucket_address(int bucket_address) {
        this.bucket_address = bucket_address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(long admin_id) {
        this.admin_id = admin_id;
    }
    public int getDataIsRight() {
        return dataIsRight;
    }

    public void setDataIsRight(int dataIsRight) {
        this.dataIsRight = dataIsRight;
    }
    
    public int getRecord_count() {
        return record_count;
    }

    public void setRecord_count(int record_count) {
        this.record_count = record_count;
    }

    @Override
	public String toString() {
		return "Bucket [id=" + id + ", bucket_code=" + bucket_code
				+ ", manufactor_id=" + manufactor_id + ", product_code="
				+ product_code + ", customer_id=" + customer_id
				+ ", bucket_address=" + bucket_address + ", status=" + status
				+ ", depot_code=" + depot_code + ", create_time=" + create_time
				+ ", checked=" + checked + ", admin_id=" + admin_id
				+ ", idTime=" + idTime + ", outInStatus=" + outInStatus + ", record_count=" + record_count +"]";
	}

}
