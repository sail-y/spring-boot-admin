package com.dmc.model;

import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

@Data
public class Role implements java.io.Serializable {

    private Long id;
    private Long pid;
    private String pname;
    private String name;
    private String remark;
    private Integer seq;

    @Transient
    private List<Long> resourceIds;
    @Transient
    private List<String> resourceNames;


}
