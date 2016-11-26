package com.dmc.controller;

import com.dmc.model.RestResponse;
import com.dmc.model.SessionInfo;
import com.dmc.model.User;
import com.dmc.service.UserService;
import com.dmc.util.AppConst;
import com.dmc.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 用户登录
     *
     * @param user    用户对象
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RestResponse login( User user, HttpSession session, HttpServletRequest request) {
        RestResponse resp = new RestResponse();
        User u = userService.login(user);
        if (u != null) {
            resp.setMsg("登陆成功！");

            SessionInfo sessionInfo = new SessionInfo();
            BeanUtils.copyProperties(u, sessionInfo);
            sessionInfo.setIp(IpUtil.getIpAddr(request));
            sessionInfo.setResourceList(userService.resourceList(u.getId()));
            session.setAttribute(AppConst.SESSION_NAME, sessionInfo);

            resp.setData(sessionInfo);
        } else {
            resp.setMsg("用户名或密码错误！");
        }
        return resp;
    }

    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public RestResponse reg(User user) {
        RestResponse response = new RestResponse();
        userService.reg(user);
        response.setMsg("注册成功！新注册的用户没有任何权限，请让管理员赋予权限后再使用本系统！");
        response.setData(user);
        return response;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public RestResponse logout(HttpSession session) {
        RestResponse resp = new RestResponse();
        if (session != null) {
            session.invalidate();
        }
        resp.setMsg("注销成功！");
        return resp;
    }


    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse add(User user) {
        RestResponse resp = new RestResponse();
        userService.add(user);
        resp.setMsg("添加成功！");
        resp.setData(user);
        return resp;
    }


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse edit(User user) {
        RestResponse resp = new RestResponse();
        userService.edit(user);
        resp.setMsg("编辑成功！");
        resp.setData(user);
        return resp;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public RestResponse delete(String id, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        RestResponse resp = new RestResponse();
        if (id != null && !id.equalsIgnoreCase(sessionInfo.getId())) {// 不能删除自己
            userService.delete(id);
        }
        resp.setMsg("删除成功！");

        return resp;
    }

    /**
     * 批量删除用户
     *
     * @param ids ('0','1','2')
     * @return
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse batchDelete(String ids, HttpSession session) {
        RestResponse resp = new RestResponse();
        if (ids != null && ids.length() > 0) {
            for (String id : ids.split(",")) {
                if (id != null) {
                    this.delete(id, session);
                }
            }
        }
        resp.setMsg("批量删除成功！");

        return resp;
    }

    /**
     * 用户授权
     *
     * @param ids
     * @return
     */
    @RequestMapping("/grant")
    @ResponseBody
    public RestResponse grant(String ids, User user) {
        RestResponse resp = new RestResponse();
        userService.grant(ids, user);

        resp.setMsg("授权成功！");
        return resp;
    }


    /**
     * 编辑用户密码
     *
     * @param user
     * @return
     */
    @RequestMapping("/editPwd")
    @ResponseBody
    public RestResponse editPwd(User user) {
        RestResponse resp = new RestResponse();
        userService.editPwd(user);

        resp.setMsg("编辑成功！");
        return resp;
    }


    /**
     * 修改自己的密码
     *
     * @param session
     * @param pwd
     * @return
     */
    @RequestMapping("/editCurrentUserPwd")
    @ResponseBody
    public RestResponse editCurrentUserPwd(HttpSession session, String oldPwd, String pwd) {
        RestResponse resp = new RestResponse();
        if (session != null) {
            SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
            if (sessionInfo != null) {
                if (userService.editCurrentUserPwd(sessionInfo, oldPwd, pwd)) {

                    resp.setMsg("编辑密码成功，下次登录生效！");
                } else {
                    resp.setMsg("原密码错误！");
                }
            } else {
                resp.setMsg("登录超时，请重新登录！");
            }
        } else {
            resp.setMsg("登录超时，请重新登录！");
        }
        return resp;
    }


}
