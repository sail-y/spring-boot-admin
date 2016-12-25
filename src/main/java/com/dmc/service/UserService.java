package com.dmc.service;

import com.dmc.jwt.AuthTokenDetails;
import com.dmc.model.User;
import com.dmc.vo.DataTable;
import com.dmc.vo.UserVO;

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
    User get(Long id);

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
    void delete(Long id);

    /**
     * 用户授权
     *
     * @param user 需要user.roleIds的属性值
     */
    void grant(User user);

    /**
     * 获得用户能访问的资源地址
     *
     * @param id 用户ID
     * @return
     */
    List<String> resourceList(Long id);

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
    boolean editCurrentUserPwd(Long currUid, String oldPwd, String pwd);


    List<String> getUserRoleNames(Long id);

    DataTable<User> tables(UserVO userVO);
}
