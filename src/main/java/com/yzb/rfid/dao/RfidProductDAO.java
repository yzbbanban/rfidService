package com.yzb.rfid.dao;

import java.util.List;


import com.yzb.rfid.entity.Product;
public interface RfidProductDAO {

	/**
	 * 查询用户信息到数据库
	 * 
	 * @param id
	 * @return
	 */
	List<Product> getRfidProductList(String id);
	/**
	 * 根据公司查询产品信息
	 * @param comp
	 * @return
	 */
	List<Product> getRfidProductListByComp(String comp);
	
	/**
	 * 根据产品 code查询数量
	 * @param productCode
	 * @return
	 */
	int getRfidProductByCode(String productCode);
}
