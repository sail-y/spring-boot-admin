package com.dmc.model;

import lombok.Data;

@Data
public class Role implements java.io.Serializable {

    private String id;
    private String pid;
    private String pname;
    private String name;
    private String remark;
    private Integer seq;
    private String iconCls;

    private String resourceIds;
    private String resourceNames;


}
