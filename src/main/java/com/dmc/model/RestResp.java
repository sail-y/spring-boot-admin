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


    private RestResp() {

    }

    private RestResp(Object data) {
        this.data = data;
    }

    private RestResp(Integer code, String msg, String path) {
        this.code = code;
        this.msg = msg;
        this.path = path;
        this.timestamp = new Date();
    }

    private RestResp(Integer code, String msg) {
        this(code, msg, null);
    }


    public static RestResp ok(String msg) {
        return new RestResp(OK, msg);
    }

    public static RestResp error(Integer code, String msg) {
        return new RestResp(code, msg);
    }

    public static RestResp error(Integer code, String msg, String path) {
        return new RestResp(code, msg, path);
    }
}
