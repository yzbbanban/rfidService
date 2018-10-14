package com.yzb.rfid.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzb.rfid.consts.ServiceResult;
import com.yzb.rfid.entity.User;
import com.yzb.rfid.service.UserService;
import com.yzb.rfid.service.exception.PasswordException;
import com.yzb.rfid.service.exception.UserNameException;
import com.yzb.rfid.service.exception.UserNotFoundException;
import com.yzb.rfid.util.ResultCode;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	// 登录
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<User> login(String name, String password) {
		// System.out.println("user: " + name + "," + password);
		User user = userService.login(name, password);
		// System.out.println("--->"+user);
		return new ResultCode(user, "登录成功");
	}

	// 获取所有用户
	@RequestMapping(value = "/getUserInfo.do", method = RequestMethod.GET)
	@ResponseBody
	public ResultCode<List<User>> getUser() {
		List<User> users = userService.getUserInfo();
		if (users == null) {
			return new ResultCode(ServiceResult.GET_MESSAGE_FALSE.getIndex(), "失败");
		}
		return new ResultCode(users, "获取成功");
	}

	// 更新用户信息
	@RequestMapping(value = "/updateUser.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultCode<List<User>> updateUser(User user) {
		int row = userService.updateUser(user);
		if (row <= 0) {
			return new ResultCode(ServiceResult.GET_MESSAGE_FALSE.getIndex(), "失败");
		}
		return new ResultCode(row, "更新成功");
	}

	// 无用户
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public ResultCode<String> handleUserNotFound(UserNotFoundException e) {
		// e.printStackTrace();
		return new ResultCode(ServiceResult.GET_MESSAGE_FALSE.getIndex(), e);
	}

	// 密码错误
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public ResultCode<String> handlePassword(PasswordException e) {
		// e.printStackTrace();
		return new ResultCode(ServiceResult.GET_MESSAGE_FALSE.getIndex(), e);
	}

	// 用户名有误
	@ExceptionHandler(UserNameException.class)
	@ResponseBody
	public ResultCode<String> handleUserName(UserNameException e) {
		// e.printStackTrace();
		return new ResultCode(ServiceResult.GET_MESSAGE_FALSE.getIndex(), e);
	}

}
