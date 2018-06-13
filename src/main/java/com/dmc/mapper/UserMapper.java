package com.dmc.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dmc.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by yangfan on 2015/5/2.
 * 用户操作Mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 登录
     * @param params 参数，用户名和密码
     * @return 用户
     */
    User login(Map<String, Object> params);

    /**
     * 统计用户名
     * @param name 用户名
     * @return 数量
     */
    int countUserName(String name);

    /**
     * 新增用户
     * @param user 用户
     */
    void save(User user);

    /**
     * 查询用户
     * @param params 查询参数
     * @return
     */
    List<User> findUser(Map<String, Object> params);

    /**
     * 获得用户的角色
     * @param id 用户ID
     * @return 角色ID
     */
    List<Long> getUserRoleIds(Long id);

    /**
     * 获得用户的角色名字
     * @param id 用户ID
     * @return 角色名称
     */
    List<String> getUserRoleNames(Long id);

    /**
     * ID查询用户
     * @param id ID
     * @return 用户
     */
    User getById(Long id);

    /**
     * 更新用户
     * @param user 用户
     */
    void update(User user);

    /**
     * 删除用户
     * @param id 用户ID
     */
    void deleteById(Long id);

    /**
     * 删除用户的角色
     * @param id 用户ID
     */
    void deleteRoles(Long id);

    /**
     * 保存用户角色
     * @param id 用户Id
     * @param roleIds 角色
     */
    void saveRoles(@Param("id") Long id, @Param("roleIds") List<Long> roleIds);


}
