package com.dmc.model;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

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
    @TableField(exist = false)
    private List<Long> roleIds;
    @TableField(exist = false)
    private List<String> roleNames;

    private String oldPassword;

}
