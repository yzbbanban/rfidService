package com.yzb.rfid.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzb.rfid.dao.RfidUpdateDAO;
import com.yzb.rfid.entity.UpdateMsg;
import com.yzb.rfid.service.RfidUpdateService;

@Service
public class RfidUpdaterServiceImpl implements RfidUpdateService {

	@Resource
	RfidUpdateDAO rfidUpdateDAO;

	public String getRfidUpdateMsgTask(String version) {
		String v =rfidUpdateDAO.getUpdateMsg(version);
		return v;
	}

	public String setRfidUpdateMsgTask(String version) {
		System.out.println(version);
		UpdateMsg msg=new UpdateMsg();
		msg.setCreateTime(new Date());
		msg.setVersion(version);
		int m=rfidUpdateDAO.setUpdateMsg(msg);
		if(m==1){
			return "插入成功";
		}else{
			return "插入失败";
		}
		
	}

}
