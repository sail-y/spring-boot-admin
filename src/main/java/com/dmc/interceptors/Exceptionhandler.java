package com.dmc.interceptors;

import com.dmc.model.RestResp;
import com.dmc.util.AppConst;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Created by yangfan on 2015/5/3.
 */
@ControllerAdvice
@Slf4j
public class Exceptionhandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RestResp processUnauthenticatedException(NativeWebRequest request, Exception e) {
        RestResp response = new RestResp();
        response.setCode(AppConst.ERROR);
        if(e.getMessage() != null) {
            response.setMsg(e.getMessage());
        }else {
            response.setMsg(Throwables.getStackTraceAsString(e));
        }
        log.error(e.getMessage(), e);
        return response;
    }
}
