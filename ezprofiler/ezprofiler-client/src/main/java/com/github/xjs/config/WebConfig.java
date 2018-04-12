package com.github.xjs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.xjs.ezprofiler.Interceptor.EzProfilerInterceptor;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年4月12日 下午3:33:34<br/>
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new EzProfilerInterceptor()).addPathPatterns("/**");
	}

}
