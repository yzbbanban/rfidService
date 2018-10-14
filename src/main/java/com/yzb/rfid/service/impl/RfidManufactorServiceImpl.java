package com.yzb.rfid.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzb.rfid.dao.RfidManufactorDAO;
import com.yzb.rfid.entity.Manufacture;
import com.yzb.rfid.service.RfidManufactorService;
import com.yzb.rfid.service.exception.RfidUserException;

@Service
public class RfidManufactorServiceImpl implements RfidManufactorService {

	@Resource
	RfidManufactorDAO rfidManufactorDAO;

	public List<Manufacture> getRfidManufactorTask(String id) {
		List<Manufacture> rusList = rfidManufactorDAO.getRfidManufactorListByComp(id);
		if (rusList != null && rusList.size() > 0) {
			return rusList;
		} else {
			throw new RfidUserException("无数据请添加");
		}
	}

}
