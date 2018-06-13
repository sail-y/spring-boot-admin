package com.dmc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dmc.model.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by yangfan on 2015/5/2.
 * 资源操作Mapper
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 获取资源列表
     *
     * @param params 参数
     * @return
     */
    List<Resource> getResourceList(Map<String, Object> params);

    /**
     * 增加资源
     *
     * @param resource
     */
    void save(Resource resource);

    /**
     * 级联删除
     *
     * @param id 主键
     */
    void deleteById(Long id);

    /**
     * 编辑资源
     *
     * @param resource 资源
     */
    void update(Resource resource);

    /**
     * 通过ID获得资源
     *
     * @param id ID
     * @return 资源
     */
    Resource getById(Long id);

    /**
     * 查询角色的资源ID
     * @param roleId 角色ID
     * @return 资源ID
     */
    List<Long> getRoleResourceIds(Long roleId);

    /**
     * 角色的资源名称集合
     * @param roleId 角色ID
     * @return 资源名称
     */
    List<String> getRoleResourceNames(Long roleId);
}
