package com.yzb.rfid.service;

import java.util.List;

import com.yzb.rfid.entity.RfidUser;


public interface RfidUserService {


	/**
	 * 查询用户信息到数据库
	 * 
	 * @param id
	 * @return
	 */
	List<RfidUser> getRfidUserTask(String id);


}
