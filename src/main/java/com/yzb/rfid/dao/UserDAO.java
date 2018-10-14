package com.yzb.rfid.dao;

import java.util.List;


import com.yzb.rfid.entity.User;

public interface UserDAO {
	/**
	 * 查询名字
	 * 
	 * @param name
	 * @return
	 */
	User findUserByName(String name);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	List<User> findUsers();

	/**
	 * 根据id查询用户
	 * 
	 * @param userId
	 * @return
	 */
	User findUserById(String userId);

	/**
	 * 更新用户类型
	 * 
	 * @param user
	 * @return
	 */
	int updateUserById(User user);
}
