package com.market.service;

import com.market.domain.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll(Integer page, Integer size);

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
     * 根据userId查询未关联的角色信息
     *
     * @param userId
     * @return
     */
    List<Role> findRolesNotAssociated(String userId);
}
