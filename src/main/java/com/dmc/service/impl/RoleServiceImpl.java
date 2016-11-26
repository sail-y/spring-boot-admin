package com.dmc.service.impl;

import com.dmc.model.Menu;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.dmc.mapper.ResourceMapper;
import com.dmc.mapper.RoleMapper;
import com.dmc.mapper.UserMapper;
import com.dmc.model.Role;
import com.dmc.model.SessionInfo;
import com.dmc.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public void add(Role role, SessionInfo sessionInfo) {

        roleMapper.save(role);

        // 刚刚添加的角色，赋予给当前的用户
        userMapper.saveRoles(sessionInfo.getId(), new String[]{role.getId()});
    }

    @Override
    public Role get(String roleId) {
        Role role = roleMapper.getById(roleId);
        List<String> resourceIds =  resourceMapper.getRoleResourceIds(roleId);
        List<String> resourceNames =  resourceMapper.getRoleResourceNames(roleId);

        role.setResourceIds(Joiner.on(",").join(resourceIds));
        role.setResourceNames(Joiner.on(",").join(resourceNames));
        return role;
    }

    @Override
    public void edit(Role role) {
        roleMapper.update(role);
    }


    @Override
    public List<Role> treeGrid(SessionInfo sessionInfo) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (sessionInfo != null) {
            params.put("userId", sessionInfo.getId());// 查自己有权限的角色
        }

        List<Role> roles = roleMapper.getRoleList(params);

        roles.forEach(role -> {
            List<String> resourceIds = resourceMapper.getRoleResourceIds(role.getId());
            List<String> resourceNames = resourceMapper.getRoleResourceNames(role.getId());

            role.setResourceIds(Joiner.on(",").join(resourceIds));
            role.setResourceNames(Joiner.on(",").join(resourceNames));
        });
        return roles;
    }

    @Override
    public void delete(String id) {
        // PID的外键为级联删除
        roleMapper.deleteById(id);
    }


    @Override
    public List<Role> tree(SessionInfo sessionInfo) {
        List<Role> list = new ArrayList<>();

        Map<String, Object> params = new HashMap<String, Object>();
        if (sessionInfo != null) {
            params.put("userId", sessionInfo.getId());// 查自己有权限的角色
        }

        List<Role> roles = roleMapper.getRoleList(params);


        return list;
    }

    @Override
    public List<Role> allTree() {
        return this.tree(null);
    }

    @Override
    public void grant(Role role) {
        roleMapper.deleteRoleResources(role.getId());

        if(!Strings.isNullOrEmpty(role.getResourceIds())){
            roleMapper.saveRoleResources(role.getId(), role.getResourceIds().split(","));
        }
    }

}
