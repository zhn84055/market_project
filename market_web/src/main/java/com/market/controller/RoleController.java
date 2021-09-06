package com.market.controller;

import com.github.pagehelper.PageInfo;
import com.market.domain.Permission;
import com.market.domain.Role;
import com.market.service.PermissionService;
import com.market.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有角色信息
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(
            @RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = true, defaultValue = "4") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.findAll(page, size);
        //pageHelper 分页，PageInfo是一个分页bean
        PageInfo pageInfo = new PageInfo(roleList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("role-list");
        return modelAndView;
    }

    /**
     * 添加新角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 根据roleId查找角色
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/findByRoleId.do")
    public ModelAndView findByRoleId(@RequestParam("id") String roleId) {
        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.findByRoleId(roleId);
        modelAndView.addObject("role", role);
        modelAndView.setViewName("role-show");
        return modelAndView;
    }

    /**
     * 根据 roleId 删除角色信息
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/deleteRoleById.do")
    public String deleteRoleById(@RequestParam("id") String roleId) {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }

    /**
     * 根据 roleId 查询 role 和 permissions
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleAndPermissionsByRoleId.do")
    public ModelAndView findRoleAndPermissionsByRoleId(@RequestParam("id") String roleId) {
        ModelAndView modelAndView = new ModelAndView();
        //根据roleId查询 role角色信息
        Role role = roleService.findByRoleId(roleId);
        //根据roleId查询该role未关联的权限信息
        List<Permission> permissionList = permissionService.findPermissionsNotAssociated(roleId);
        modelAndView.addObject("role", role);
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }

    /**
     * 添加<role,permission>的映射关系 —— 给 role 添加 permission
     *
     * @return
     */
    @RequestMapping("/addPermissionsToRole.do")
    public String addPermissionsToRole(
            @RequestParam("roleId") String roleId,
            @RequestParam("ids") String[] permissionIds) {
        permissionService.addPermissionsToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
}
