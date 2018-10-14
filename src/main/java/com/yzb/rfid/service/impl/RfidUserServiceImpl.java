package com.yzb.rfid.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yzb.rfid.dao.RfidUserDAO;
import com.yzb.rfid.entity.RfidUser;
import com.yzb.rfid.service.RfidUserService;
import com.yzb.rfid.service.exception.RfidUserException;

@Service
public class RfidUserServiceImpl implements RfidUserService {

	@Resource
	RfidUserDAO rfidUserDAO;

	public List<RfidUser> getRfidUserTask(String id) {
		List<RfidUser> rusList = rfidUserDAO.getRfidUserListByComp(id);
		if (rusList != null && rusList.size() > 0) {
			return rusList;
		} else {
			throw new RfidUserException("无数据请添加");
		}
	}

}
