package com.yzb.rfid.service;

import java.util.List;

import com.yzb.rfid.entity.Product;

public interface RfidProductService {


	/**
	 * 查询用户信息到数据库
	 * 
	 * @param id
	 * @return
	 */
	List<Product> getRfidProductTask(String id);


}
