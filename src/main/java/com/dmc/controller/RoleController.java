package com.dmc.controller;

import com.dmc.model.RestResp;
import com.dmc.model.Role;
import com.dmc.jwt.AuthTokenDetails;
import com.dmc.service.RoleService;
import com.dmc.util.AppConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping("/add")
	@ResponseBody
	public RestResp add(Role role) {
		RestResp j = new RestResp();
		roleService.add(role);

		j.setMsg("添加成功！");
		return j;
	}

	/**
	 * 修改角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public RestResp edit(Role role) {
		RestResp j = new RestResp();
		roleService.edit(role);

		j.setMsg("编辑成功！");
		return j;
	}

	/**
	 * 获得角色列表
	 * 
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Role> treeGrid() {
		return roleService.treeGrid();
	}

	/**
	 * 角色(只能看到自己拥有的角色)
	 * 
	 * @return
	 */
	@RequestMapping("/tree")
	@ResponseBody
	public List<Role> tree() {
		return roleService.roles();
	}

	/**
	 * 角色
	 * 
	 * @return
	 */
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Role> allTree() {
		return roleService.allRole();
	}

	/**
	 * 删除角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RestResp delete(Long id) {
		RestResp j = new RestResp();
		roleService.delete(id);
		j.setMsg("删除成功！");

		return j;
	}


	/**
	 * 授权
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public RestResp grant(Role role) {
		RestResp j = new RestResp();
		roleService.grant(role);
		j.setMsg("授权成功！");

		return j;
	}

}
