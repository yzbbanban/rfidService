package com.yzb.rfid.service;


public interface RfidUpdateService {


	/**
	 * 查询更新的数据
	 * 
	 * @param version
	 * @return
	 */
	String getRfidUpdateMsgTask(String version);

	/**
	 * 插入更新的数据
	 * 
	 * @param version
	 * @return
	 */
	String setRfidUpdateMsgTask(String version);

}
