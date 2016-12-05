/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.dmc.exception;

/**
 * Created by YangFan on 2016/11/28 下午2:44.
 * <p/>
 */
public class WrongPathException extends RuntimeException {

    public WrongPathException() {
        super("你访问的接口不存在或未配置！");
    }
}
