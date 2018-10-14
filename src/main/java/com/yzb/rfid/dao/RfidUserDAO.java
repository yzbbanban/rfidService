package com.yzb.rfid.dao;

import java.util.List;


import com.yzb.rfid.entity.RfidUser;

public interface RfidUserDAO {

    /**
     * 查询用户信息到数据库
     *
     * @param id
     * @return
     */
    List<RfidUser> getRfidUserList(String id);

    /**
     * 根据公司获取客户列表
     *
     * @param comp
     * @return
     */
    List<RfidUser> getRfidUserListByComp(String comp);
}
