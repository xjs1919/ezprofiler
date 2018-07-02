package com.github.xjs.ezprofiler.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerMapping;

import com.github.xjs.ezprofiler.controller.EzProfilerController;
import com.github.xjs.ezprofiler.mapping.PropertySourcedRequestMappingHandlerMapping;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年7月2日 上午8:11:01<br/>
 */
@Configuration
@ComponentScan(basePackages = 
	{ 
	"com.github.xjs.ezprofiler.config", 
	"com.github.xjs.ezprofiler.controller",
	"com.github.xjs.ezprofiler.scanner" })
public class EzProfilerConfiguration {

	@Autowired
	private EzProfilerProperties properties;
	
	@Bean
	public HandlerMapping ezprofilerControllerMapping(Environment environment) {
		return new PropertySourcedRequestMappingHandlerMapping(environment, new EzProfilerController(properties));
	}
}
