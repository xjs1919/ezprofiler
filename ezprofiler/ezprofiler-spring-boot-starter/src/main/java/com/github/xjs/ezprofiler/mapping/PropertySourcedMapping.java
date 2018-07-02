package com.github.xjs.ezprofiler.mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年7月2日 上午8:42:22<br/>
 */
@Target({ ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Mapping
public @interface PropertySourcedMapping {
  String propertyKey();
  String value();
}
