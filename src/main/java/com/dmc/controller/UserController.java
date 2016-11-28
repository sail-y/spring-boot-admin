package com.dmc.controller;

import com.dmc.dto.AuthTokenDTO;
import com.dmc.jwt.AuthTokenDetails;
import com.dmc.jwt.JsonWebTokenUtility;
import com.dmc.model.RestResp;
import com.dmc.model.User;
import com.dmc.service.UserService;
import com.dmc.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    private JsonWebTokenUtility tokenService = new JsonWebTokenUtility();

    /**
     * 用户登录
     *
     * @param u 用户对象
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthTokenDTO login(@RequestBody User u) {
        AuthTokenDTO authToken = null;

        User user = userService.login(u);

        if (user != null) {


            AuthTokenDetails authTokenDetails = new AuthTokenDetails();
            authTokenDetails.setId(user.getId());
            authTokenDetails.setUsername(user.getUsername());
            authTokenDetails.setExpirationDate(buildExpirationDate());
            authTokenDetails.setRoleNames(userService.getUserRoleNames(user.getId()));

            // Create auth token
            String jwt = tokenService.createJsonWebToken(authTokenDetails);
            if (jwt != null) {
                authToken = new AuthTokenDTO();
                authToken.setToken(jwt);
                authToken.setUserId(user.getId());
                authToken.setResourceList(userService.resourceList(user.getId()));
            }
        } else {
            throw new RuntimeException("用户名或密码错误");
        }

        return authToken;
    }


    private Date buildExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
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
    public void delete(String id) {
        String currUid = SessionUtil.getCurrUid();
        if (id != null && !id.equalsIgnoreCase(currUid)) {// 不能删除自己
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
                    this.delete(id);
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
    public RestResp editPwd(@RequestBody User user) {
        userService.editPwd(user);
        return new RestResp();
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
        String currUid = SessionUtil.getCurrUid();

        if (currUid != null) {
            if (!userService.editCurrentUserPwd(currUid, user.getOldPassword(), user.getPassword())) {
                throw new RuntimeException("原密码错误！");
            }
        } else {
            throw new RuntimeException("登录超时，请重新登录！");
        }

        return new RestResp();
    }


}
