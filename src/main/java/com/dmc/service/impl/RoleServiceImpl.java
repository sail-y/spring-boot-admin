package com.dmc.service.impl;

import com.dmc.mapper.ResourceMapper;
import com.dmc.mapper.RoleMapper;
import com.dmc.mapper.UserMapper;
import com.dmc.model.Role;
import com.dmc.service.RoleService;
import com.dmc.util.SessionUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public void add(Role role) {

        roleMapper.save(role);

        // 刚刚添加的角色，赋予给当前的用户
        userMapper.saveRoles(SessionUtil.getCurrUid(), Collections.singletonList(role.getId()));
    }

    @Override
    public Role get(Long roleId) {
        Role role = roleMapper.getById(roleId);
        List<Long> resourceIds = resourceMapper.getRoleResourceIds(roleId);
        List<String> resourceNames = resourceMapper.getRoleResourceNames(roleId);

        role.setResourceIds(Joiner.on(",").join(resourceIds));
        role.setResourceNames(Joiner.on(",").join(resourceNames));
        return role;
    }

    @Override
    public void edit(Role role) {
        roleMapper.update(role);
    }


    @Override
    public List<Role> treeGrid() {
        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null) {
            params.put("userId", currUid);// 查自己有权限的角色
        }

        List<Role> roles = roleMapper.getRoleList(params);

        roles.forEach(role -> {
            List<Long> resourceIds = resourceMapper.getRoleResourceIds(role.getId());
            List<String> resourceNames = resourceMapper.getRoleResourceNames(role.getId());

            role.setResourceIds(Joiner.on(",").join(resourceIds));
            role.setResourceNames(Joiner.on(",").join(resourceNames));
        });
        return roles;
    }

    @Override
    public void delete(Long id) {
        // PID的外键为级联删除
        roleMapper.deleteById(id);
    }


    @Override
    public List<Role> roles() {

        Map<String, Object> params = new HashMap<>();
        Long currUid = SessionUtil.getCurrUid();
        if (currUid != null) {
            params.put("userId", currUid);// 查自己有权限的角色
        }

        List<Role> roles = roleMapper.getRoleList(params);


        return roles;
    }

    @Override
    public List<Role> allRole() {
        return roleMapper.getRoleList(new HashMap<>());
    }

    @Override
    public void grant(Role role) {
        roleMapper.deleteRoleResources(role.getId());

        if (!Strings.isNullOrEmpty(role.getResourceIds())) {
            List<String> strings = Splitter.on(",").splitToList(role.getResourceIds());
            roleMapper.saveRoleResources(role.getId(), strings.stream().map(Long::valueOf).collect(Collectors.toList()));
        }
    }

}
