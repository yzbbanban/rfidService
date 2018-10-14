package com.yzb.rfid.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzb.rfid.consts.ServiceResult;
import com.yzb.rfid.entity.Manufacture;
import com.yzb.rfid.service.RfidManufactorService;
import com.yzb.rfid.service.exception.RfidUserException;
import com.yzb.rfid.util.ResultCode;

@Controller
@RequestMapping("/rfidManufactor")
public class RfidManufactorController {
	@Resource
	RfidManufactorService service;

	/**
	 * 获取客户列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getRfidManufactorList.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<List<Manufacture>> getRfidUser(String id) {
		System.out.println(id);
		List<Manufacture> manufactures = service.getRfidManufactorTask(id);
		return new ResultCode(manufactures, "获取成功");
	}

	// 添加错误
	@ExceptionHandler(RfidUserException.class)
	@ResponseBody
	public ResultCode<String> handleMessage(RfidUserException e) {
//		e.printStackTrace();
		return new ResultCode(ServiceResult.GET_MESSAGE_FALSE.getIndex(), e);
	}
}
