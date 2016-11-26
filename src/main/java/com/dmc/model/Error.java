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
public class Error implements java.io.Serializable {

    // 默认成功
    private Integer code = 0;

    private String msg;

    private Object data;

    public Error() {

    }

    public Error(Object data) {
        this.data = data;
    }

    public Error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
