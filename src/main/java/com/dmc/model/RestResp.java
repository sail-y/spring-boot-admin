package com.dmc.model;

import com.dmc.util.AppConst;
import lombok.Data;

/**
 * Error
 * <p>
 * Error
 *
 * @author yangfan
 */
@Data
public class RestResp implements java.io.Serializable {

    // 默认成功
    private Integer code = AppConst.OK;

    private String msg;

    private Object data;

    public RestResp() {

    }

    public RestResp(Object data) {
        this.data = data;
    }

    public RestResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
