/*
 * Copyright (c) 2016 xiaomaihd and/or its affiliates.All Rights Reserved.
 *            http://www.xiaomaihd.com
 */
package com.dmc.interceptors;

import com.dmc.model.RestResp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by YangFan on 16/3/17 下午5:28.
 * <p/>
 */

@Aspect
@Slf4j
@Component
public class RestInterceptor {

    /**
     * 拦截所有的Controller类
     */
    @Pointcut("within(com.dmc..controller.*Controller)")
    public void type() {
    }

    /**
     * 拦截所有的public方法
     */
    @Pointcut("execution(public * *(..))")
    public void anyMethod() {
    }


    /**
     * 拦截所有接口，进行日志记录
     *
     * @param joinPoint
     */
    @Around(value = "type() && anyMethod()", argNames = "joinPoint")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        Object result = joinPoint.proceed();
        return new RestResp(result);
    }


}
/*
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG
*/
