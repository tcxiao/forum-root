package com.forum.action;

public class BaseAction {
	
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
