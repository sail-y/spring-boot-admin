/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.ddff.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by YangFan on 2016/10/14 下午5:48.
 * <p/>
 */
@Mapper
public interface UserMapper {
    List<Long> getUserIds();
}
