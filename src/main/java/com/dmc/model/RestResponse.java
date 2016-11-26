package com.dmc.model;

import com.dmc.util.AppConst;
import lombok.Data;

/**
 * JSON模型
 * <p>
 * 用户后台向前台返回的JSON对象
 *
 * @author yangfan
 */
@Data
public class RestResponse implements java.io.Serializable {

    // 默认成功
    private Integer status = AppConst.OK;

    private String msg;

    private Object data;


}
