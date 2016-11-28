package com.dmc.service;

import com.dmc.jwt.AuthTokenDetails;
import com.dmc.model.User;

import java.util.List;

/**
 * 用户Service
 *
 * @author yangfan
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param user 里面包含登录名和密码
     * @return 用户对象
     */
    User login(User user);


    /**
     * 添加用户
     *
     * @param user
     */
    void add(User user);

    /**
     * 获得用户对象
     *
     * @param id
     * @return
     */
    User get(String id);

    /**
     * 编辑用户
     *
     * @param user
     */
    void edit(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    void delete(String id);

    /**
     * 用户授权
     *
     * @param ids
     * @param user 需要user.roleIds的属性值
     */
    void grant(String ids, User user);

    /**
     * 获得用户能访问的资源地址
     *
     * @param id 用户ID
     * @return
     */
    List<String> resourceList(String id);

    /**
     * 编辑用户密码
     *
     * @param user
     */
    void editPwd(User user);

    /**
     * 修改用户自己的密码
     *
     * @param currUid
     * @param oldPwd
     * @param pwd
     * @return
     */
    boolean editCurrentUserPwd(String currUid, String oldPwd, String pwd);


    List<String> getUserRoleNames(String id);
}
