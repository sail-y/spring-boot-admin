package com.dmc.service.impl;

import com.dmc.util.AppConst;
import com.dmc.util.SessionUtil;
import com.google.common.base.Strings;
import com.dmc.mapper.ResourceMapper;
import com.dmc.mapper.RoleMapper;
import com.dmc.mapper.UserMapper;
import com.dmc.model.Resource;
import com.dmc.jwt.AuthTokenDetails;
import com.dmc.model.Menu;
import com.dmc.service.ResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.Session;
import java.util.*;

@Service("resourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Menu> menus() {

        List<Menu> menuList = new ArrayList<>();

        Map<String, Object> params = new HashMap<>();
        params.put("type", AppConst.RESOURCE_TYPE_MENU);// 菜单类型的资源
        String currUid = SessionUtil.getCurrUid();
        if (currUid != null) {
            params.put("userId", currUid);// 只查自己有权限的资源
        }

        List<Resource> resourceList = resourceMapper.getResourceList(params);

        assembleMenu(menuList, resourceList);
        return menuList;
    }

    private void assembleMenu(List<Menu> menuList, List<Resource> resourceList) {
        for (Resource r : resourceList) {
            if(r.getPid() == null) {
                Menu menu = new Menu();
                BeanUtils.copyProperties(r, menu);
                menu.setText(r.getName());
                List<Menu> children = new ArrayList<>();
                for (Resource r1 : resourceList) {
                    if(Objects.equals(r1.getPid(), r.getId())) {
                        Menu child = new Menu();
                        BeanUtils.copyProperties(r1, child);
                        child.setText(r1.getName());
                        children.add(child);
                    }
                }
                menu.setChildren(children);
                menuList.add(menu);
            }
        }
    }

    @Override
    public List<Menu> allTree() {
        List<Menu> menuList = new ArrayList<Menu>();

        Map<String, Object> params = new HashMap<>();
        List<Resource> resourceList = resourceMapper.getResourceList(params);
        assembleMenu(menuList, resourceList);
        return menuList;
    }

    @Override
    public List<Resource> treeGrid() {

        Map<String, Object> params = new HashMap<>();
        String currUid = SessionUtil.getCurrUid();

        if (currUid != null) {
            params.put("userId", currUid);// 自查自己有权限的资源
        }

        List<Resource> resourceList = resourceMapper.getResourceList(params);

        Map<String, Resource> map = new HashMap<>();
        resourceList.forEach(resource -> map.put(resource.getId(), resource));
        resourceList.forEach(resource -> resource.setPname(resource.getPid() != null ? map.get(resource.getPid()).getName() : null));

        return resourceList;
    }

    @Override
    public void add(Resource resource) {

        if (Strings.isNullOrEmpty(resource.getPid())) {
            resource.setPid(null);
        }
        resourceMapper.save(resource);

        // 由于当前用户所属的角色，没有访问新添加的资源权限，所以在新添加资源的时候，将当前资源授权给当前用户的所有角色，以便添加资源后在资源列表中能够找到
        String userId = SessionUtil.getCurrUid();
        List<String> roleIds = userMapper.getUserRoleIds(userId);
        roleIds.forEach(roleId -> roleMapper.saveRoleResources(roleId, new String[]{resource.getId()}));

    }

    /**
     * 删除
     *
     * @param id id
     */
    @Override
    public void delete(String id) {
        resourceMapper.deleteById(id);
    }


    @Override
    public void edit(Resource resource) {
        if (Strings.isNullOrEmpty(resource.getPid())) {
            resource.setPid(null);
        }
        resourceMapper.update(resource);
    }


    @Override
    public Resource get(String id) {

        return resourceMapper.getById(id);
    }

    @Override
    public List<Resource> getResourceList(Map<String, Object> params) {
        return resourceMapper.getResourceList(params);
    }

}
