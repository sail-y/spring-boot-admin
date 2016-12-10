package com.dmc.model;

import lombok.Data;

@Data
public class Resource implements java.io.Serializable {

	private Long id;
	private String type;
	private Long pid;
	private String pname;
	private String method;
	private String name;
	private String remark;
	private Integer seq;
	private String url;
	private String roleId;
	private String roleName;



}
