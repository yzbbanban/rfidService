package com.yzb.rfid.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzb.rfid.dao.RfidProductDAO;
import com.yzb.rfid.entity.Product;
import com.yzb.rfid.service.RfidProductService;
import com.yzb.rfid.service.exception.RfidUserException;

@Service
public class RfidProductServiceImpl implements RfidProductService{

	@Resource
	RfidProductDAO rfidProductDAO;

	public List<Product> getRfidProductTask(String id) {
		List<Product> rusList = rfidProductDAO.getRfidProductListByComp(id);
		if (rusList != null && rusList.size() > 0) {
			return rusList;
		} else {
			throw new RfidUserException("无数据请添加");
		}
	}

}
