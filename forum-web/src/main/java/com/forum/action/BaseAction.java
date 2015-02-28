package com.forum.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseAction {
	
	@ExceptionHandler
	public String exception(HttpServletRequest request,Exception ex){
		ex.printStackTrace();
		if(ex instanceof NumberFormatException){
			return "number";
		}
		return "error";
	}
	
	/**
	 * 设置JSON字符串信息
	 * @param flag
	 * @param msg
	 * @return
	 */
	public String setJsonMsg(boolean flag,String msg){
		return "{\"success\":"+flag+",\"msg\":\""+ msg +"\"}";
	}

}
