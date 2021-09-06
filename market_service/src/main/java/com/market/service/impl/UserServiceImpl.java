package com.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.market.dao.RoleDao;
import com.market.dao.UserInfoDao;
import com.market.domain.Role;
import com.market.domain.UserInfo;
import com.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userInfoDao.findUserByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的用户对象封装成UserDetails
        // User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

    //返回一个List集合，集合中装入角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add((new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        }
        return list;
    }


    /**
     * 根据用户名查询用户信息
     *
     * @return
     */
    @Override
    public UserInfo findUserByName(String userName) {
        return userInfoDao.findUserByName(userName);
    }

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<UserInfo> findAll(Integer page, Integer size) {
        //分页
        PageHelper.startPage(page, size);
        return userInfoDao.findAll();
    }

    /**
     * 添加新用户
     *
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) {
        //对密码进行加密处理
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userInfoDao.save(userInfo);
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(String id) {
        return userInfoDao.findById(id);
    }

    /**
     * 添加<user,role>的映射关系 —— 给user添加 role
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void addRolesToUser(String userId, String[] roleIds) {
        //逐个添加映射
        for (String roleId : roleIds) {
            userInfoDao.addRoleToUser(userId, roleId);
        }
    }

}
