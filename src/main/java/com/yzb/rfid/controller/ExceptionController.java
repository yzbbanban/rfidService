package com.yzb.rfid.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzb.rfid.service.RfidExceptionService;
import com.yzb.rfid.util.ResultCode;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

	@Resource
	RfidExceptionService rfidExceptionService;
	// 登录
	@RequestMapping(value = "/rfidException.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<String> sendException(String exception) {
		String result=rfidExceptionService.addBucketListTask(exception);
		return new ResultCode(result, "异常上传完成");
	}

}
