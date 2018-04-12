package com.github.xjs.ezprofiler.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.github.xjs.ezprofiler.annotation.Profiler;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年4月12日 下午3:07:39<br/>
 */
public class EzProfilerInterceptor extends HandlerInterceptorAdapter{

	public static final String REQUEST_ATTR_NAME_PROFILER = "_profiler_";
	public static final String REQUEST_ATTR_NAME_OCCUR_ERROR = "_occurError_";
	
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(EzProfilerInterceptor.class);
	
	private ProfilerQueue queue = new ProfilerQueue();
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod hm = (HandlerMethod)handler;
		Profiler methodProfiler = hm.getMethodAnnotation(Profiler.class);
		if(methodProfiler == null) {
			Profiler controllerProfiler = hm.getBean().getClass().getAnnotation(Profiler.class);
			if(controllerProfiler == null) {//启用
				startProfile(request, hm);
			}else {
				boolean enable = controllerProfiler.value();
				if(enable) {
					startProfile(request, hm);
				}else {
				}
			}
		}else {
			boolean enable = methodProfiler.value();
			if(enable) {
				startProfile(request, hm);
			}else {
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(
			HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		endProfile(request, (HandlerMethod)handler, ex);
	}
	
	private void startProfile(HttpServletRequest request, HandlerMethod hm) {
		ProfileInfo info = new ProfileInfo();
		info.setStart(System.currentTimeMillis());
		info.setUri(request.getRequestURI());
		info.setHandlerMethod(hm);
		request.setAttribute(REQUEST_ATTR_NAME_PROFILER, info);
	}
	
	private void endProfile(HttpServletRequest request, HandlerMethod handler, Exception ex) {
		ProfileInfo info = (ProfileInfo)request.getAttribute(REQUEST_ATTR_NAME_PROFILER);
		if(info == null) {
			return;
		}
		info.setEnd(System.currentTimeMillis());
		//有可能异常已经被@ControllerAdvice给处理掉了，此时需要在request中设置_occurError_
		boolean occurError = (ex!=null);
		if(!occurError) {
			Boolean hasError = (Boolean)request.getAttribute(REQUEST_ATTR_NAME_OCCUR_ERROR);
			if(hasError != null) {
				occurError = hasError;
			}
		}
		info.setOccurError(occurError);
		//入队
		queue.addProfileInfo(info);
	}
}
