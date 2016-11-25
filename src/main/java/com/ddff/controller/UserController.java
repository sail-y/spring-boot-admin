/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.ddff.controller;

import com.ddff.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by YangFan on 2016/10/14 下午6:01.
 * <p/>
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/userList")
    public List<Long> userId() {
        List<Long> userIds = userMapper.getUserIds();
        return userIds;
    }

}
