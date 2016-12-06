package com.dmc.controller;

import com.dmc.model.RestResp;
import com.dmc.model.Role;
import com.dmc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制器
 *
 * @author yangfan
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /**
     * 添加角色
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public RestResp add(@RequestBody Role role) {
        roleService.add(role);
        return new RestResp(RestResp.OK, "添加成功！");
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public RestResp edit(@RequestBody Role role) {
        roleService.edit(role);
        return new RestResp(RestResp.OK, "更新成功！");
    }

    /**
     * get role by id
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
    public Role getById(@PathVariable("roleId") Long roleId) {

        return roleService.get(roleId);
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
    public RestResp delete(@PathVariable("roleId") Long roleId) {
        roleService.delete(roleId);

        return new RestResp(RestResp.OK, "删除成功！");
    }


    /**
     * 授权
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    public RestResp grant(@RequestBody Role role) {

        roleService.grant(role);

        return new RestResp(RestResp.OK, "授权成功！");
    }


}
