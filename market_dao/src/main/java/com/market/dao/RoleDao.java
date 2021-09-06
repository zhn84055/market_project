package com.market.dao;

import com.market.domain.Role;

import java.util.List;

/**
 * 角色
 */
public interface RoleDao {

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 通过UserId查询角色
     *
     * @return
     */
    List<Role> findRoleByUserId(String UserId);

    /**
     * 添加新角色
     *
     * @param role
     * @return
     */
    void save(Role role);

    /**
     * 根据roleId查找角色
     *
     * @param roleId
     * @return
     */
    Role findByRoleId(String roleId);

    /**
     * 根据 roleId 删除角色信息
     *
     * @param roleId
     * @return
     */
    void deleteRoleById(String roleId);

    /**
     * 根据roleId删除User_Role的映射
     *
     * @param roleId
     */
    void deleteFromUser_RoleByRoleId(String roleId);

    /**
     * 根据roleId删除Role_Permission的映射
     *
     * @param roleId
     */
    void deleteFromRole_PermissionByRoleId(String roleId);

    /**
     * 根据userId查询未关联的角色信息
     *
     * @param userId
     * @return
     */
    List<Role> findRolesNotAssociated(String userId);
}
