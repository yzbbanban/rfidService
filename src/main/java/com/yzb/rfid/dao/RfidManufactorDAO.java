package com.yzb.rfid.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yzb.rfid.entity.Manufacture;
public interface RfidManufactorDAO {
	/**
	 * 查询用户信息到数据库
	 * 
	 * @param name
	 * @return
	 */
	Manufacture getRfidManufactor(String name);
	/**
	 * 查询用户信息到数据库
	 * 
	 * @param id
	 * @return
	 */
	List<Manufacture> getRfidManufactorList(String id);
	/**
	 * 按照公司查询用户信息
	 * 
	 * @param comp
	 * @return
	 */
	List<Manufacture> getRfidManufactorListByComp(String comp);
}
