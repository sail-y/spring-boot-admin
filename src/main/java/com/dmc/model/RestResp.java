package com.dmc.model;

import lombok.Data;

import java.util.Date;

/**
 * Error
 * <p>
 * Error
 *
 * @author yangfan
 */
@Data
public class RestResp implements java.io.Serializable {


    public static final Integer OK = 200;
    public static final Integer ERROR = 500;
    public static final Integer NO_PERMISSION = 10001;
    public static final Integer NO_SESSION = 10002;
    public static final Integer NOT_FOUND = 404;

    // 默认成功
    private Integer code = OK;

    private String msg;

    private Object data;
    private String path;
    private Date timestamp;

    public RestResp() {

    }

    public RestResp(Object data) {
        this.data = data;
    }

    public RestResp(Integer code, String msg, String path) {
        this.code = code;
        this.msg = msg;
        this.path = path;
        this.timestamp = new Date();
    }

    public RestResp(Integer code, String msg) {
        this(code, msg, null);
    }
}
