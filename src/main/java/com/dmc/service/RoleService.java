package com.dmc.service;

import com.dmc.model.Role;
import com.dmc.jwt.AuthTokenDetails;
import com.dmc.model.User;
import com.dmc.vo.DataTable;
import com.dmc.vo.RoleVO;

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
	void add(Role role);

	/**
	 * 获得角色
	 * 
	 * @param id
	 * @return
	 */
	Role get(Long id);

	/**
	 * 编辑角色
	 * 
	 * @param role
	 */
	void edit(Role role);

	/**
	 * 获得角色treeGrid
	 * 
	 * @return
	 */
	List<Role> treeGrid();

	/**
	 * 删除角色
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 获得角色(只能看到自己拥有的角色)
	 * 
	 * @return
	 */
	List<Role> roles();

	/**
	 * 获得角色
	 * 
	 * @return
	 */
	List<Role> allRole();

	/**
	 * 为角色授权
	 * 
	 * @param role
	 */
	void grant(Role role);


    DataTable<Role> tables(RoleVO roleVO);

	List<Role> tree();
}
