package com.dmc.model;

import lombok.Data;

import java.util.List;

@Data
public class Tree implements java.io.Serializable {

	private String id;
	private String text;
	private String state = "open";// open,closed
	private boolean checked = false;
	private Object attributes;
	private List<Tree> children;
	private String iconCls;
	private String pid;
}
