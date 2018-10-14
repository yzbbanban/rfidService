package com.yzb.rfid.service;

import java.util.List;

import com.yzb.rfid.entity.User;
import com.yzb.rfid.service.exception.PasswordException;
import com.yzb.rfid.service.exception.UserNotFoundException;


/**
 * 业务层接口
 */
public interface UserService {
    /**
     * 登录功能, 登录成功返回用户信息, 登录失败
     * 则抛出异常.
     *
     * @param name     用户名
     * @param password 密码
     * @return 如果登录成功就返回登录用户信息
     * @throws UserNotFoundException 用户不存在
     * @throws PasswordException     密码错误
     */
    User login(String name, String password)
            throws UserNotFoundException,
            PasswordException;

    /**
     * 获取所有用户
     *
     * @return
     */
    List<User> getUserInfo();

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);
}



