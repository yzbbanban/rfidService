package com.yzb.rfid.dao;



import com.yzb.rfid.entity.RfidException;

public interface RfidExceptionDAO {
	/**
	 * 添加异常信息
	 * 
	 * @param rfidException
	 * @return
	 */
	int addOrderListTask(RfidException rfidException);
	
}
