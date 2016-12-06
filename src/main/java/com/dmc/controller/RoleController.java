package com.dmc.controller;

import com.dmc.model.RestResp;
import com.dmc.model.Role;
import com.dmc.jwt.AuthTokenDetails;
import com.dmc.service.RoleService;
import com.dmc.util.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 角色控制器
 * 
 * @author yangfan
 * 
 */
@Controller
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
	@ResponseBody
	public RestResp add(@RequestBody Role role) {
		roleService.add(role);
		return new RestResp(RestResp.OK,"添加成功！");
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public RestResp edit(@RequestBody Role role) {
		roleService.edit(role);
		return new RestResp(RestResp.OK,"更新成功！");
	}



	/**
	 * 删除角色
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
	@ResponseBody
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
	@ResponseBody
	public RestResp grant(Role role) {
		RestResp j = new RestResp();
		roleService.grant(role);
		j.setMsg("授权成功！");

		return j;
	}

}
