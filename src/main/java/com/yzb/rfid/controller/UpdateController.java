package com.yzb.rfid.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzb.rfid.service.RfidUpdateService;
import com.yzb.rfid.util.ResultCode;

@Controller
@RequestMapping("/rfidUpdate")
public class UpdateController {

	@Resource
	RfidUpdateService service;
	
	// 更新
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<String>  update(String version) {
		String msg=service.getRfidUpdateMsgTask(version);
		return new ResultCode(msg, msg);
	}

	// 写入更新
	@RequestMapping(value = "/addUpdate.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<String> addUpdate(String version) {
		System.out.println(version);
		String msg=service.setRfidUpdateMsgTask(version);
		return new ResultCode(msg, msg);
	}

}
