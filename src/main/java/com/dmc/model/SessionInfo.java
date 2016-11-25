package com.dmc.model;

import lombok.Data;

import java.util.List;

/**
 * session信息模型
 *
 * @author yangfan
 */
@Data
public class SessionInfo implements java.io.Serializable {

    private String id;// 用户ID
    private String name;// 用户登录名
    private String ip;// 用户IP

    private List<String> resourceList;// 用户可以访问的资源地址列表

}
