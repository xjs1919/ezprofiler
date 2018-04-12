package com.github.xjs.ezprofiler.Interceptor;

import org.springframework.web.method.HandlerMethod;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年4月12日 下午4:11:29<br/>
 */
public class ProfileInfo {
	private String uri;
	private long start;
	private long end;
	private boolean occurError;
	private HandlerMethod method;
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	public boolean isOccurError() {
		return occurError;
	}
	public void setOccurError(boolean occurError) {
		this.occurError = occurError;
	}
	public HandlerMethod getHandlerMethod() {
		return method;
	}
	public void setHandlerMethod(HandlerMethod handlerMethod) {
		this.method = handlerMethod;
	}
}	
