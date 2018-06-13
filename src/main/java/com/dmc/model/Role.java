package com.dmc.model;

import com.baomidou.mybatisplus.annotations.TableField;
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

    @TableField(exist = false)
    private List<Long> resourceIds;
    @TableField(exist = false)
    private List<String> resourceNames;


}
