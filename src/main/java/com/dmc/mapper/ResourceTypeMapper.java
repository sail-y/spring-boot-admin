package com.dmc.mapper;

import com.dmc.model.ResourceType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by yangfan on 2015/5/2.
 */
@Mapper
public interface ResourceTypeMapper {

    /**
     * 获取资源类型列表
     *
     * @return
     */
    List<ResourceType> getResourceTypeList();

    /**
     * 通过ID获得资源类型
     *
     * @param id
     * @return
     */
    ResourceType getById(String id);


}
