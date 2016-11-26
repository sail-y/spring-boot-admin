package com.dmc.model;

import lombok.Data;

import java.util.Date;

@Data
public class User implements java.io.Serializable {

    private String id;
    private String username;
    private String name;
    private String password;
    private Date createTime;
    private Date modifyTime;
    private String roleIds;
    private String roleNames;

}
