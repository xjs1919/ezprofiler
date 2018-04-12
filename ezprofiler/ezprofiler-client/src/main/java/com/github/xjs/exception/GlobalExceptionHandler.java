package com.github.xjs.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.xjs.ezprofiler.Interceptor.EzProfilerInterceptor;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
    @ExceptionHandler(value= {Exception.class, RuntimeException.class} )  
    public String allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception{  
        request.setAttribute(EzProfilerInterceptor.REQUEST_ATTR_NAME_OCCUR_ERROR, true);	
    	return "服务端异常";
    }  
}
