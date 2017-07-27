package com.dmc.model;

import lombok.Data;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
public class User implements java.io.Serializable {

    private Long id;
    private String username;
    private String name;
    private String password;
    private Date createTime;
    private Date modifyTime;
    @Transient
    private List<Long> roleIds;
    @Transient
    private List<String> roleNames;

    private String oldPassword;

}
