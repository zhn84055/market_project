package com.market.dao;

import com.market.domain.Permission;

import java.util.List;

public interface PermissionDao {

    /**
     * 查询所有权限
     *
     * @return
     */
    List<Permission> findAll();

    /**
     * 根据 role id查询权限
     *
     * @return
     */
    List<Permission> findPermissionByRoleId();

    /**
     * 新增资源权限
     *
     * @return
     */
    void save(Permission permission);

    /**
     * 根据permissionId删除权限
     *
     * @return
     */
    void deletePermissionById(String permissionId);

    /**
     * 根据permissionId删除Role_Permission映射关系
     *
     * @param permissionId
     */
    void deleteFromRole_PermissionByPermissionId(String permissionId);

    /**
     * 根据 roleId 查询 role 和 permissions
     *
     * @param roleId
     * @return
     */
    List<Permission> findPermissionsNotAssociated(String roleId);

    /**
     * 添加<role,permission>的映射关系 —— 给 role 添加 permission
     *
     * @return
     */
    void addPermissionToRole(String roleId, String permissionId);
}
