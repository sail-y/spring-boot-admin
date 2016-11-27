package com.dmc.controller;

import com.dmc.model.RestResp;
import com.dmc.model.SessionInfo;
import com.dmc.model.User;
import com.dmc.service.UserService;
import com.dmc.util.AppConst;
import com.dmc.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    /**
     * 用户登录
     *
     * @param user    用户对象
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public SessionInfo login(@RequestBody User user, HttpServletRequest request) {
        User u = userService.login(user);
        if (u != null) {
            SessionInfo sessionInfo = new SessionInfo();
            BeanUtils.copyProperties(u, sessionInfo);
            sessionInfo.setIp(IpUtil.getIpAddr(request));
            sessionInfo.setResourceList(userService.resourceList(u.getId()));
            session.setAttribute(AppConst.SESSION_NAME, sessionInfo);
            return sessionInfo;
        } else {
            throw new RuntimeException("用户名或密码错误");
        }

    }


    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public RestResp logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }

        return new RestResp();
    }


    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public User add(User user) {
        userService.add(user);
        return user;
    }


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public User edit(User user) {
        userService.edit(user);
        return user;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public void delete(String id, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        if (id != null && !id.equalsIgnoreCase(sessionInfo.getId())) {// 不能删除自己
            userService.delete(id);
        }
    }

    /**
     * 批量删除用户
     *
     * @param ids ('0','1','2')
     * @return
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public void batchDelete(String ids, HttpSession session) {
        if (ids != null && ids.length() > 0) {
            for (String id : ids.split(",")) {
                if (id != null) {
                    this.delete(id, session);
                }
            }
        }
    }

    /**
     * 用户授权
     *
     * @param ids
     * @return
     */
    @RequestMapping("/grant")
    @ResponseBody
    public void grant(String ids, User user) {
        userService.grant(ids, user);
    }


    /**
     * 编辑用户密码
     *
     * @param user
     * @return
     */
    @RequestMapping("/editPwd")
    @ResponseBody
    public void editPwd(User user) {
        userService.editPwd(user);
    }


    /**
     * 修改自己的密码
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/editCurrentUserPwd", method = RequestMethod.POST)
    @ResponseBody
    public RestResp editCurrentUserPwd(@RequestBody User user) {
        if (session != null) {
            SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
            if (sessionInfo != null) {
                if (!userService.editCurrentUserPwd(sessionInfo, user.getOldPassword(), user.getPassword())) {
                    throw new RuntimeException("原密码错误！");
                }
            } else {
                throw new RuntimeException("登录超时，请重新登录！");
            }
        } else {
            throw new RuntimeException("登录超时，请重新登录！");
        }

        return new RestResp();
    }


}
