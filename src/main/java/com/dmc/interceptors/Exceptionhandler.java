package com.dmc.interceptors;

import com.dmc.model.RestResponse;
import com.dmc.util.AppConst;
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
    public RestResponse processUnauthenticatedException(NativeWebRequest request, Exception e) {
        RestResponse response = new RestResponse();
        response.setStatus(AppConst.ERROR);
        response.setMsg(e.getMessage());
        log.error(e.getMessage(), e);
        return response;
    }
}
