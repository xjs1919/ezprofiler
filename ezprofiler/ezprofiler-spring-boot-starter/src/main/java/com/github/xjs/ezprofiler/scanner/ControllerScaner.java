package com.github.xjs.ezprofiler.scanner;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xjs.ezprofiler.annotation.Profiler;
import com.github.xjs.ezprofiler.config.EzProfilerProperties;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年6月29日 下午12:59:59<br/>
 */
@Service
public class ControllerScaner implements BeanPostProcessor{
	
	private static Logger log = LoggerFactory.getLogger(ControllerScaner.class);
	
	@Autowired
	EzProfilerProperties properties;
	
	private ProfilerQueue queue = new ProfilerQueue();
	
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}
	
	/**
	 * 拦截Controller和RestController类，生成他们的子类
	 * */
	public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
		final Class<?> beanClass = bean.getClass();
		final String beanClassName = beanClass.getName();
		if(beanClassName.startsWith("org.springframework") || beanClassName.indexOf("EzProfilerController")>=0) {
			return bean;
		}
		Controller controllerAnno = AnnotationUtils.findAnnotation(beanClass, Controller.class);
		RestController restControllerAnno = AnnotationUtils.findAnnotation(beanClass, RestController.class);
		if(controllerAnno == null && restControllerAnno == null) {
			return bean;
		}
		Profiler profiler = AnnotationUtils.findAnnotation(beanClass, Profiler.class);
		if(profiler!=null && !profiler.enable()) {//类上没有启用profiler
			return bean;
		}
		log.info("find controller:{}", beanName);
		Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanClass);
        enhancer.setCallback(new MethodInterceptor() {
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				Profiler methodProfiler = AnnotationUtils.findAnnotation(method, Profiler.class);
				//方法上没有启用
				if(methodProfiler != null && !methodProfiler.enable()) {
					return proxy.invokeSuper(obj, args);
				}
				//不是一个requestMapping方法
				GetMapping getMappingAnnotation = AnnotationUtils.findAnnotation(method, GetMapping.class);
				PostMapping postMappingAnnotation = AnnotationUtils.findAnnotation(method, PostMapping.class);
				RequestMapping requestMappingAnnotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);
				if(requestMappingAnnotation == null && getMappingAnnotation == null && postMappingAnnotation == null) {
					return proxy.invokeSuper(obj, args);
				}
				//开始统计
				String uri = null;
				long startAt = 0;
				long endAt = 0;
				boolean occurError=true;
				try {
					if(getMappingAnnotation !=null) {
						uri = getMappingAnnotation.value()[0];
					}else if(postMappingAnnotation != null) {
						uri = postMappingAnnotation.value()[0];
					}else if(requestMappingAnnotation != null) {
						uri = requestMappingAnnotation.value()[0];
					}else {
						throw new RuntimeException("impossible");
					}
					startAt = System.currentTimeMillis();
					Object result = proxy.invokeSuper(obj, args);
					endAt = System.currentTimeMillis();
					occurError = false;
					return result;
				}catch(Exception e) {
					endAt = System.currentTimeMillis();
					occurError = true;
					throw e;
				}finally {
					ProfileInfo info = new ProfileInfo();
					info.setStart(startAt);
					info.setEnd(endAt);
					info.setUri(uri);
					info.setClazz(beanClass);
					info.setMethod(method);
					info.setOccurError(occurError);
					//入队
					queue.addProfileInfo(info);
				}
			}
        });
		return enhancer.create();
	}
}
