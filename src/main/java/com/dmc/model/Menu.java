package com.dmc.model;

import lombok.Data;

import java.util.List;

@Data
public class Menu implements java.io.Serializable {

	private String id;
	private String text;
	private String url;
	private List<Menu> children;
}
