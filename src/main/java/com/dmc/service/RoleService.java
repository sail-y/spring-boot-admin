package com.dmc.service;

import com.dmc.model.Role;
import com.dmc.jwt.AuthTokenDetails;

import java.util.List;

/**
 * 角色业务逻辑
 * 
 * @author yangfan
 * 
 */
public interface RoleService {

	/**
	 * 保存角色
	 * 
	 * @param role
	 */
	public void add(Role role);

	/**
	 * 获得角色
	 * 
	 * @param id
	 * @return
	 */
	public Role get(Long id);

	/**
	 * 编辑角色
	 * 
	 * @param role
	 */
	public void edit(Role role);

	/**
	 * 获得角色treeGrid
	 * 
	 * @return
	 */
	public List<Role> treeGrid();

	/**
	 * 删除角色
	 * 
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 获得角色(只能看到自己拥有的角色)
	 * 
	 * @return
	 */
	public List<Role> roles();

	/**
	 * 获得角色
	 * 
	 * @return
	 */
	public List<Role> allRole();

	/**
	 * 为角色授权
	 * 
	 * @param role
	 */
	public void grant(Role role);


}
