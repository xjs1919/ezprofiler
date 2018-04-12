package com.github.xjs.ezprofiler.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年4月12日 下午3:03:48
 */
public class WebUtil {
	
	public static void ret401(HttpServletRequest request, HttpServletResponse response){
		String serverName = request.getServerName();
		response.setStatus(401);
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setHeader("WWW-authenticate", "Basic Realm=\"" + serverName + "\"");
	}
}
