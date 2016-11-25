package com.dmc.controller;

import com.alibaba.fastjson.JSON;
import com.dmc.model.*;
import com.dmc.service.ResourceService;
import com.dmc.service.RoleService;
import com.dmc.service.UserService;
import com.dmc.util.AppConst;
import com.dmc.util.IpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户控制器
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    /**
     * 用户登录
     *
     * @param user    用户对象
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public JsonModel login(User user, HttpSession session, HttpServletRequest request) {
        JsonModel j = new JsonModel();
        User u = userService.login(user);
        if (u != null) {
            j.setSuccess(true);
            j.setMsg("登陆成功！");

            SessionInfo sessionInfo = new SessionInfo();
            BeanUtils.copyProperties(u, sessionInfo);
            sessionInfo.setIp(IpUtil.getIpAddr(request));
            sessionInfo.setResourceList(userService.resourceList(u.getId()));
            session.setAttribute(AppConst.SESSION_NAME, sessionInfo);

            j.setObj(sessionInfo);
        } else {
            j.setMsg("用户名或密码错误！");
        }
        return j;
    }

    /**
     * 用户注册
     *
     * @param user 用户对象
     * @return
     */
    @ResponseBody
    @RequestMapping("/reg")
    public JsonModel reg(User user) {
        JsonModel j = new JsonModel();
        try {
            userService.reg(user);
            j.setSuccess(true);
            j.setMsg("注册成功！新注册的用户没有任何权限，请让管理员赋予权限后再使用本系统！");
            j.setObj(user);
        } catch (Exception e) {
            // e.printStackTrace();
            j.setMsg(e.getMessage());
        }
        return j;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/logout")
    public JsonModel logout(HttpSession session) {
        JsonModel j = new JsonModel();
        if (session != null) {
            session.invalidate();
        }
        j.setSuccess(true);
        j.setMsg("注销成功！");
        return j;
    }


    /**
     * 添加用户
     *
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public JsonModel add(User user) {
        JsonModel j = new JsonModel();
        try {
            userService.add(user);
            j.setSuccess(true);
            j.setMsg("添加成功！");
            j.setObj(user);
        } catch (Exception e) {
            // e.printStackTrace();
            j.setMsg(e.getMessage());
        }
        return j;
    }


    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public JsonModel edit(User user) {
        JsonModel j = new JsonModel();
        try {
            userService.edit(user);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
            j.setObj(user);
        } catch (Exception e) {
            // e.printStackTrace();
            j.setMsg(e.getMessage());
        }
        return j;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonModel delete(String id, HttpSession session) {
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        JsonModel j = new JsonModel();
        if (id != null && !id.equalsIgnoreCase(sessionInfo.getId())) {// 不能删除自己
            userService.delete(id);
        }
        j.setMsg("删除成功！");
        j.setSuccess(true);
        return j;
    }

    /**
     * 批量删除用户
     *
     * @param ids ('0','1','2')
     * @return
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public JsonModel batchDelete(String ids, HttpSession session) {
        JsonModel j = new JsonModel();
        if (ids != null && ids.length() > 0) {
            for (String id : ids.split(",")) {
                if (id != null) {
                    this.delete(id, session);
                }
            }
        }
        j.setMsg("批量删除成功！");
        j.setSuccess(true);
        return j;
    }

    /**
     * 用户授权
     *
     * @param ids
     * @return
     */
    @RequestMapping("/grant")
    @ResponseBody
    public JsonModel grant(String ids, User user) {
        JsonModel j = new JsonModel();
        userService.grant(ids, user);
        j.setSuccess(true);
        j.setMsg("授权成功！");
        return j;
    }


    /**
     * 编辑用户密码
     *
     * @param user
     * @return
     */
    @RequestMapping("/editPwd")
    @ResponseBody
    public JsonModel editPwd(User user) {
        JsonModel j = new JsonModel();
        userService.editPwd(user);
        j.setSuccess(true);
        j.setMsg("编辑成功！");
        return j;
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
    public JsonModel editCurrentUserPwd(HttpSession session, String oldPwd, String pwd) {
        JsonModel j = new JsonModel();
        if (session != null) {
            SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
            if (sessionInfo != null) {
                if (userService.editCurrentUserPwd(sessionInfo, oldPwd, pwd)) {
                    j.setSuccess(true);
                    j.setMsg("编辑密码成功，下次登录生效！");
                } else {
                    j.setMsg("原密码错误！");
                }
            } else {
                j.setMsg("登录超时，请重新登录！");
            }
        } else {
            j.setMsg("登录超时，请重新登录！");
        }
        return j;
    }



    @RequestMapping("/printSession")
    @ResponseBody
    public JsonModel printSession(String name, HttpSession session) {
        System.out.println("fuck");
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(AppConst.SESSION_NAME);
        JsonModel j = new JsonModel();
        j.setMsg(JSON.toJSONString(sessionInfo));
        j.setSuccess(true);
        return j;
    }
}
