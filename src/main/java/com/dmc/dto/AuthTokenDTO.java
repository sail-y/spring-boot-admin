/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.dmc.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by YangFan on 2016/11/28 上午10:53.
 * <p/>
 */
@Data
public class AuthTokenDTO {
    private String token;
    private Long userId;
    private List<String> resourceList;
    private String name;

}
