package com.yzb.rfid.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.jrockit.jfr.Producer;
import com.yzb.rfid.consts.ServiceResult;
import com.yzb.rfid.entity.Product;
import com.yzb.rfid.entity.RfidUser;
import com.yzb.rfid.service.RfidProductService;
import com.yzb.rfid.service.RfidUserService;
import com.yzb.rfid.service.exception.RfidUserException;
import com.yzb.rfid.util.ResultCode;

@Controller
@RequestMapping("/rfidProduct")
public class RfidProductController {
	@Resource
	RfidProductService service;

	/**
	 * 获取客户列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getRfidProductList.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<List<Product>> getRfidProduct(String id) {
		System.out.println(id);
		List<Product> products=service.getRfidProductTask(id);
		return new ResultCode(products, "获取成功");
	}
	
	
	
	// 添加错误
	@ExceptionHandler(RfidUserException.class)
	@ResponseBody
	public ResultCode<String> handleMessage(RfidUserException e) {
//		e.printStackTrace();
		return new ResultCode(ServiceResult.GET_MESSAGE_FALSE.getIndex(), e);
	}

}
