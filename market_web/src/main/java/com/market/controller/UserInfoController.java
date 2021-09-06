package com.market.controller;

import com.github.pagehelper.PageInfo;
import com.market.dao.RoleDao;
import com.market.domain.Role;
import com.market.domain.UserInfo;
import com.market.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleDao roleDao;


    /**
     * 查询所有用户信息
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")//ADMIN角色的用户才能访问
    public ModelAndView findAll(
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> userList = userService.findAll(page, size);
        //PageInfo是一个分页bean
        PageInfo pageInfo = new PageInfo(userList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 新增用户
     *
     * @return
     */
    @RequestMapping("/save.do")
//    @PreAuthorize("authentication.principal.username == 'tom'")//tom用户才能访问该方法
    public String save(UserInfo userInfo) {
        userService.save(userInfo);
        //重定向，查询数据
        return "redirect:findAll.do";
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        modelAndView.addObject("user", userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    /**
     * 根据userId查询userInfo 和 roles
     *
     * @return
     */
    @RequestMapping("/findUserAndRolesByUserId.do")
    public ModelAndView findUserAndRolesByUserId(@RequestParam("id") String userId) {
        ModelAndView modelAndView = new ModelAndView();
        //根据userId查询UserInfo
        final UserInfo userInfo = userService.findById(userId);
        //根据userId查询未关联的角色信息
        List<Role> roleList = roleDao.findRolesNotAssociated(userId);
        //封装user、roles信息
        modelAndView.addObject("user", userInfo);
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     * 添加<user,role>的映射关系 —— 给user添加 role
     *
     * @return
     */
    @RequestMapping("/addRolesToUser.do")
    public String addRolesToUser(
            @RequestParam(value = "userId", required = true) String userId,
            @RequestParam(value = "ids", required = true) String[] roleIds) {
        userService.addRolesToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
