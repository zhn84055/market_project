package com.market.service;

import com.market.domain.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 查询所有权限
     *
     * @param page
     * @param size
     * @return
     */
    List<Permission> findAll(Integer page, Integer size);

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
    void addPermissionsToRole(String roleId, String[] permissionIds);
}
