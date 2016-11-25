package com.dmc.service;

import com.dmc.model.ResourceType;

import java.util.List;

/**
 * 资源类型服务
 * 
 * @author yangfan
 * 
 */
public interface ResourceTypeService {

	/**
	 * 获取资源类型
	 * 
	 * @return
	 */
	public List<ResourceType> getResourceTypeList();

}
