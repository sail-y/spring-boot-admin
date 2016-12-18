package com.dmc.service;

import com.dmc.model.Resource;
import com.dmc.jwt.AuthTokenDetails;
import com.dmc.model.Menu;

import java.util.List;
import java.util.Map;

/**
 * 资源Service
 * 
 * @author yangfan
 * 
 */
public interface ResourceService {

	/**
	 * 获得资源树(资源类型为菜单类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @return
	 */
	public List<Menu> menus();

	/**
	 * 获得资源树(包括所有资源类型)
	 * 
	 * 通过用户ID判断，他能看到的资源
	 * 
	 * @return
	 */
	public List<Menu> allMenus();

	/**
	 * 获得资源列表
	 * 
	 *
	 * @return
	 */
	public List<Resource> treeList();

	/**
	 * 添加资源
	 * 
	 * @param resource
	 */
	public void add(Resource resource);

	/**
	 * 删除资源
	 * 
	 * @param id
	 */
	public void delete(Long id);

	/**
	 * 修改资源
	 * 
	 * @param resource
	 */
	public void edit(Resource resource);

	/**
	 * 获得一个资源
	 * 
	 * @param id
	 * @return
	 */
	public Resource get(Long id);

	/**
	 * 资列表
	 * @param params
	 * @return
	 */
    List<Resource> getResourceList(Map<String, Object> params);


}
