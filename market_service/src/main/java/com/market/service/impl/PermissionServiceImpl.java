package com.market.service.impl;

import com.github.pagehelper.PageHelper;
import com.market.dao.PermissionDao;
import com.market.domain.Permission;
import com.market.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询所有权限
     *
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Permission> findAll(Integer page, Integer size) {
        //PageHelper分页
        PageHelper.startPage(page, size);
        return permissionDao.findAll();
    }

    /**
     * 新增资源权限
     *
     * @return
     */
    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    /**
     * 根据permissionId删除权限
     *
     * @return
     */
    @Override
    public void deletePermissionById(String permissionId) {
        //根据permissionId删除Role_Permission映射关系
        permissionDao.deleteFromRole_PermissionByPermissionId(permissionId);
        //根据permissionId删除权限
        permissionDao.deletePermissionById(permissionId);
    }

    /**
     * 根据 roleId 查询 role 和 permissions
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findPermissionsNotAssociated(String roleId) {
        return permissionDao.findPermissionsNotAssociated(roleId);
    }

    /**
     * 添加<role,permission>的映射关系 —— 给 role 添加 permission
     *
     * @return
     */
    @Override
    public void addPermissionsToRole(String roleId, String[] permissionIds) {
        //遍历permissionIds
        for (String permissionId : permissionIds) {
            permissionDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
