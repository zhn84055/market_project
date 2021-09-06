package com.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.market.dao.RoleDao;
import com.market.domain.Role;
import com.market.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色
     *
     * @return
     */
    @Override
    public List<Role> findAll(Integer page, Integer size) {
        //分页，必须在查询方法上，中间不能有其他语句
        PageHelper.startPage(page, size);
        return roleDao.findAll();
    }

    /**
     * 添加新角色
     *
     * @param role
     * @return
     */
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    /**
     * 根据roleId查找角色
     *
     * @param roleId
     * @return
     */
    @Override
    public Role findByRoleId(String roleId) {
        return roleDao.findByRoleId(roleId);
    }

    /**
     * 根据 roleId 删除角色信息
     *
     * @param roleId
     * @return
     */
    @Override
    public void deleteRoleById(String roleId) {
        //根据roleId删除User_Role的映射
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //根据roleId删除Role_Permission的映射
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //删除角色信息
        roleDao.deleteRoleById(roleId);
    }

    /**
     * 根据userId查询未关联的角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRolesNotAssociated(String userId) {
        return roleDao.findRolesNotAssociated(userId);
    }
}
