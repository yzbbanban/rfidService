package com.yzb.rfid.dao;



import com.yzb.rfid.entity.UpdateMsg;

public interface RfidUpdateDAO {
	/**
	 * 插入更新信息
	 * 
	 * @param msg
	 * @return
	 */
	int setUpdateMsg(UpdateMsg msg);
	/**
	 * 获取更新信息
	 * 
	 * @param version
	 * @return
	 */
	String getUpdateMsg(String version);
}
