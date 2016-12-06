package com.dmc.model;

import lombok.Data;

import java.util.List;

@Data
public class Role implements java.io.Serializable {

    private Long id;
    private Long pid;
    private String pname;
    private String name;
    private String remark;
    private Integer seq;

    private String resourceIds;
    private String resourceNames;


}
