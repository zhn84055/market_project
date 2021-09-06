package com.market.service;

import com.market.domain.Role;
import com.market.domain.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

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
    List<UserInfo> findAll(Integer page, Integer size);


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
     * @param roleIds
     */
    void addRolesToUser(String userId, String[] roleIds);
}
