package com.thinkgem.jeesite.modules.sys.web;

import java.io.Serializable;
/**
 * 用于封装服务器到客户端的Json返回值
 * @author soft01
 *
 */
public class JsonResult<T> implements Serializable {
	//Serializable将对象的状态保存在存储媒体中以便可以在以后重新创建出完全相同的副本
	public static final String SUCCESS="0";
	public static final String ERROR="1";
	private String state;
	private String message = "";
	private T data;
	public JsonResult(){
		state = SUCCESS;
	}
	//为了方便，重载n个构造器
	public JsonResult(String state, String message, T data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}
	public JsonResult(String state,String error){
		this(ERROR,error,null);
	}
	public JsonResult(String state,T data){
		this(ERROR,"",data);
	}

	public JsonResult(T data){
		this(SUCCESS,"",data);
	}

	public JsonResult(Throwable e){
		this(ERROR,e.getMessage(),null);
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public static String getSuccess() {
		return SUCCESS;
	}
	public static String getError() {
		return ERROR;
	}
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
