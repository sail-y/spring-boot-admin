package com.dmc.model;

import lombok.Data;

/**
 * 
 * JSON模型
 * 
 * 用户后台向前台返回的JSON对象
 * 
 * @author yangfan
 * 
 */
@Data
public class JsonModel implements java.io.Serializable {

	private boolean success = false;

	private String msg = "";

	private Object obj = null;


}
