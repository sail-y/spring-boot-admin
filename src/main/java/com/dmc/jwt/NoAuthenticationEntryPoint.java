/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.dmc.jwt;

import com.dmc.model.RestResp;
import com.dmc.util.JsonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by YangFan on 2016/11/28 下午1:31.
 * <p/>
 */
public class NoAuthenticationEntryPoint implements AuthenticationEntryPoint {

    //当访问的资源没有权限，会调用这里
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {


        //返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        RestResp restResp = RestResp.error(RestResp.NO_SESSION, "没有登录或登录已过期!");

        response.getWriter().println(JsonUtil.toJsonString(restResp));
        response.getWriter().flush();
    }
}