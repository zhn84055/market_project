package com.market.dao;

import com.market.domain.Role;
import com.market.domain.UserInfo;

import java.util.List;

public interface UserInfoDao {

    /**
     * 根据用户名查询用户信息
     *
     * @return
     */
    UserInfo findUserByName(String userName);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 添加新用户
     *
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 添加<user,role>的映射关系 —— 给user添加 role
     *
     * @param userId
     * @param roleId
     */
    void addRoleToUser(String userId, String roleId);
}
