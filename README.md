# ezprofiler

## 统计Controller的方法执行时间

## 使用方式

1. 添加依赖
```xml
<dependency>
  <groupId>com.github.xjs</groupId>
  <artifactId>ezprofiler-spring-boot-starter</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```
2. 在自己的项目中添加拦截器EzProfilerInterceptor
```java
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new EzProfilerInterceptor()).addPathPatterns("/**");
	}
}
```
3.项目启动以后，访问浏览器：http://localhost:8080/profiler

## 其他配置

1. 默认会统计所有Controller的所有方法，可以在不想被统计的Controller类或者方法上添加@Profiler(false)注解，方法的优先级高于类的优先级
```java
@GetMapping("/world")
@Profiler(false)
public String world() {
  return "world";
}
```
2. 默认会给统计接口加上权限验证，默认的用户名：ezprofiler-admin，密码：ezprofiler-admin，可以自定义：
```html
ezprofiler.enableBasic=true
ezprofiler.username=xjs
ezprofiler.password=123456
```
3. 默认的profiler的访问路径是/profiler，可以自定义:
```html
ezprofiler.url=/my/profiler
```

