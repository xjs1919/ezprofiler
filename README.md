# ezprofiler

## 统计Controller的方法执行时间

## 使用方式

1. 添加依赖，首先手动下载源码，执行maven clean install
```xml
<dependency>
  <groupId>com.github.xjs</groupId>
  <artifactId>ezprofiler-spring-boot-starter</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```
2. 添加配置
```java
@EnableProfiler
@Configuration
public class EzProfilerConfigure {
}
```
3.项目启动以后，访问浏览器 http://localhost:8080/profiler , 输出结果类似：
```json
{
	"DemoController": [{
		"method": "hello",//方法名
		"uri": "/hello",     //url路径
		"invokeCount": 2,  //总的调用次数
		"okCount": 2,       //总的成功的次数
		"errorCount": 0,   //总的失败的次数
		"minMills": 0,       //最小用时
		"maxMills": 0,      //最大用时
		"avgMills": 0,       //平均用时
		"maxInvokeAt": "2018-08-09 10:28:08", //最大用时发生时间点
		"lastDayCount": 2,  //最近一天总调用次数
		"lastDayOkCount": 2,//最近一天成功次数
		"lastDayErrorCount": 0,//最近一天失败次数
		"lastDayMinMills": 0,//最近一天最小用时
		"lastDayMaxMills": 0,//最近一天最大用时
		"lastDayAvgMills": 0,//最近一天平均用时
		"lastDayMaxInvokeAt": "2018-08-09 10:28:12",//最近一天最大用时发生时间点
		"lastMills": 0,       //上次用时
		"lastInvokeAt": "2018-08-09 10:30:11"//上次调用时间点
	}]
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
4.可以自定义要扫描的controller的base package，默认是com
```html
ezprofiler.basepackage=com.github.xjs.controller
```
