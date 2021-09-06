package com.market.controller;

import com.github.pagehelper.PageInfo;
import com.market.domain.Permission;
import com.market.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有权限
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> list = permissionService.findAll(page, size);
        //PageInfo是一个分页bean
        PageInfo pageInfo = new PageInfo(list);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    /**
     * 新增资源权限
     *
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 根据permissionId删除权限
     *
     * @return
     */
    @RequestMapping("/deletePermissionById.do")
    public String deletePermissionById(@RequestParam("id") String permissionId) {
        permissionService.deletePermissionById(permissionId);
        return "redirect:findAll.do";
    }
}
