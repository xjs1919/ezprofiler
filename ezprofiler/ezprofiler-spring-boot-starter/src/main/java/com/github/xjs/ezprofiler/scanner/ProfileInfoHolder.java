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
			mai.setLastDayCount(1);
			if(occurError) {
				mai.setErrorCount(1);
				mai.setLastDayErrorCount(1);
			}else {
				mai.setOkCount(1);
				mai.setLastDayOkCount(1);
			}
			mai.setMinMills(useTime);
			mai.setMaxMills(useTime);
			mai.setAvgMills(useTime);
			mai.setMaxInvokeAt(new Date());
			mai.setLastDayMinMills(useTime);
			mai.setLastDayMaxMills(useTime);
			mai.setLastDayAvgMills(useTime);
			mai.setLastDayMaxInvokeAt(new Date());
			mai.setLastMills(useTime);
			mai.setLastInvokeAt(new Date());
			mais.add(mai);
		}else {//之前已经有调用
			Date lastInvokeTime = mai.getLastInvokeAt();
			Date now = new Date();
			if(lastInvokeTime.getMonth() != now.getMonth() || lastInvokeTime.getDay() != now.getDay()) {//第二天重新计算
				mai.setLastDayCount(0);
				mai.setLastDayErrorCount(0);
				mai.setLastDayOkCount(0);
				mai.setLastDayAvgMills(useTime);
				mai.setLastDayMinMills(useTime);
				mai.setLastDayMaxMills(useTime);
				mai.setLastDayMaxInvokeAt(new Date());
			}
			if(useTime < mai.getMinMills()) {
				mai.setMinMills(useTime);
			}
			if(useTime < mai.getLastDayMinMills()) {
				mai.setLastDayMinMills(useTime);
			}
			if(useTime > mai.getMaxMills()) {
				mai.setMaxMills(useTime);
				mai.setMaxInvokeAt(new Date());
			}
			if(useTime > mai.getLastDayMaxMills()) {
				mai.setLastDayMaxMills(useTime);
				mai.setLastDayMaxInvokeAt(new Date());
			}
			mai.setInvokeCount(mai.getInvokeCount() + 1);
			mai.setLastDayCount(mai.getLastDayCount() + 1);
			if(occurError) {
				mai.setErrorCount(mai.getErrorCount()+1);
				mai.setLastDayErrorCount(mai.getLastDayErrorCount()+1);
			}else {
				mai.setOkCount(mai.getOkCount()+1);
				mai.setLastDayOkCount(mai.getLastDayOkCount()+1);
			}
			mai.setAvgMills((mai.getAvgMills()+useTime)/2);
			mai.setLastDayAvgMills((mai.getLastDayAvgMills()+useTime)/2);
			mai.setLastInvokeAt(new Date());
			mai.setLastMills(useTime);
		}
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
