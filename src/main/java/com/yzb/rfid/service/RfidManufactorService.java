package com.yzb.rfid.service;

import java.util.List;

import com.yzb.rfid.entity.Manufacture;

public interface RfidManufactorService {

	/**
	 * 查询用户信息到数据库
	 * 
	 * @param id
	 * @return
	 */
	List<Manufacture> getRfidManufactorTask(String id);


}
