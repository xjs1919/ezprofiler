package com.github.xjs.ezprofiler.scanner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 605162215@qq.com
 *
 * @date 2017年9月21日 下午3:02:30<br/>
 * 
 */
public class ProfileInfoHolder {
	
	private static ConcurrentHashMap<Class<?>, ControllerAccessInfo> map = new ConcurrentHashMap<Class<?>, ControllerAccessInfo>();
	
	@SuppressWarnings("deprecation")
	public static void addProfilerInfo(ProfileInfo pi) {
		long startAt = pi.getStart();
		long endAt = pi.getEnd();
		long useTime = endAt - startAt;
		String uri = pi.getUri();
		boolean occurError = pi.isOccurError();
		Class<?> controllerClazz = pi.getClazz();
		Method method = pi.getMethod();
		ControllerAccessInfo cai = map.get(controllerClazz);
		if(cai == null) {
			cai = new ControllerAccessInfo();
			cai.setControllerClazz(controllerClazz);
			map.put(controllerClazz, cai);
		}
		List<MethodAccessInfo> mais = cai.getMethodInfos();
		if(mais == null) {
			mais = new ArrayList<MethodAccessInfo>();
			cai.setMethodInfos(mais);
		}
		MethodAccessInfo mai = getMethodAccessInfo(mais, method);
		if(mai == null) {//第一次调用
			mai = new MethodAccessInfo();
			mai.setMethod(method.getName());
			mai.setUri(uri);
			mai.setInvokeCount(1);
			mai.setTodayCount(1);
			if(occurError) {
				mai.setErrorCount(1);
				mai.setTodayErrorCount(1);
			}else {
				mai.setOkCount(1);
				mai.setTodayOkCount(1);
			}
			mai.setMinMills(useTime);
			mai.setMaxMills(useTime);
			mai.setAvgMills(useTime);
			mai.setMaxInvokeAt(new Date());
			mai.setTodayMinMills(useTime);
			mai.setTodayMaxMills(useTime);
			mai.setTodayAvgMills(useTime);
			mai.setTodayMaxInvokeAt(new Date());
			mais.add(mai);
		}else {//之前已经有调用
			Date lastInvokeTime = mai.getLastInvokeAt();
			Date now = new Date();
			if(lastInvokeTime.getMonth() != now.getMonth() || lastInvokeTime.getDay() != now.getDay()) {//第二天重新计算
				mai.setTodayCount(0);
				mai.setTodayErrorCount(0);
				mai.setTodayOkCount(0);
				mai.setTodayAvgMills(useTime);
				mai.setTodayMinMills(useTime);
				mai.setTodayMaxMills(useTime);
			}
			if(useTime < mai.getMinMills()) {
				mai.setMinMills(useTime);
			}
			if(useTime < mai.getTodayMinMills()) {
				mai.setTodayMinMills(useTime);
			}
			if(useTime > mai.getMaxMills()) {
				mai.setMaxMills(useTime);
				mai.setMaxInvokeAt(new Date());
			}
			if(useTime > mai.getTodayMaxMills()) {
				mai.setTodayMaxMills(useTime);
				mai.setTodayMaxInvokeAt(new Date());
			}
			mai.setInvokeCount(mai.getInvokeCount() + 1);
			mai.setTodayCount(mai.getTodayCount() + 1);
			if(occurError) {
				mai.setErrorCount(mai.getErrorCount()+1);
				mai.setTodayErrorCount(mai.getTodayErrorCount()+1);
			}else {
				mai.setOkCount(mai.getOkCount()+1);
				mai.setTodayOkCount(mai.getTodayOkCount()+1);
			}
			mai.setAvgMills((mai.getAvgMills()+useTime)/2);
			mai.setTodayAvgMills((mai.getTodayAvgMills()+useTime)/2);
			
		}
		mai.setLastInvokeAt(new Date());
		mai.setLastMills(useTime);
	}
	
	private static MethodAccessInfo getMethodAccessInfo(List<MethodAccessInfo> mais, Method method) {
		for(MethodAccessInfo mai : mais) {
			if(method.getName().equals(mai.getMethod())) {
				return mai;
			}
		}
		return null;
	}
	
	public static Map<String, Object> getAllAccessInfo() {
		Map<String, Object> result = new HashMap<String, Object>();
		for(Map.Entry<Class<?>, ControllerAccessInfo> entry : map.entrySet()) {
			ControllerAccessInfo cai = entry.getValue();
			result.put(cai.getControllerClazz().getSimpleName(), cai.getMethodInfos());
		}
		return result;
	}
}
