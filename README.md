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
3.项目启动以后，访问浏览器 http://localhost:8080/profiler , 输出结果类似：
```json
{
	"DemoController": [{
		"method": "hello",//方法名
		"uri": "/hello",//访问url
		"invokeCount": 4,//调用的总次数
		"okCount": 4,//成功的次数
		"errorCount": 0,//错误的次数
		"minMills": 2,//最小时间
		"maxMills": 33,//最大时间
		"avgMills": 6,//平均时间
		"todayCount": 4,//今天的调用次数
		"todayOkCount": 4,//今天成功的次数
		"todayErrorCount": 0,//今天失败的次数
		"todayMinMills": 2,//今天最小时间
		"todayMaxMills": 33,//今天最大时间
		"todayAvgMills": 6,//今天平均时间
		"lastMills": 3,//上次调用花费的时间
		"lastInvokeTime": 1523533964865//上次调用时间点
	},]
}
```

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
4. 如果自定了全局的异常处理器拦截了错误的请求，需要在request种设置EzProfilerInterceptor.REQUEST_ATTR_NAME_OCCUR_ERROR为true，否则会认为本次请求是正常请求
```java
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	@ExceptionHandler(value= {Exception.class, RuntimeException.class} )  
	public String allExceptionHandler(HttpServletRequest request, Exception exception) throws Exception{  
		request.setAttribute(EzProfilerInterceptor.REQUEST_ATTR_NAME_OCCUR_ERROR, true);	
		return "服务端异常";
	}  
}
```
