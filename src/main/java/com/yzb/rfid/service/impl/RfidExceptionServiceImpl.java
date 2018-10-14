package com.yzb.rfid.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;





import com.yzb.rfid.dao.RfidExceptionDAO;
import com.yzb.rfid.entity.RfidException;
import com.yzb.rfid.service.RfidExceptionService;

@Service
public class RfidExceptionServiceImpl implements RfidExceptionService {
	@Resource
	RfidExceptionDAO dao;

	public String addBucketListTask(String exception) {
		RfidException rfidException=new RfidException();
		rfidException.setException(exception);
		rfidException.setException_createTime(new Date());
		dao.addOrderListTask(rfidException);
		
		return "成功";
	}

	

}
