package com.dmc.model;

import lombok.Data;

@Data
public class Resource implements java.io.Serializable {

	private String id;
	private String typeId;
	private String typeName;
	private String pid;
	private String pname;
	private String name;
	private String remark;
	private Integer seq;
	private String url;
	private String roleId;
	private String roleName;


}
