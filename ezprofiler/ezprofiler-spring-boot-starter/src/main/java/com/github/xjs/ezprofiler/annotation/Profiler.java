package com.github.xjs.ezprofiler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年4月12日 下午3:49:23<br/>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Profiler {
	
	@AliasFor("enable")   
	boolean value() default true;
	
	@AliasFor("value")    
	boolean enable() default true;
}
