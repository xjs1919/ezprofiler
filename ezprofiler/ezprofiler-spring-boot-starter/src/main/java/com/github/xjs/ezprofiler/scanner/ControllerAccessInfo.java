package com.github.xjs.ezprofiler.scanner;

import java.util.List;

/**
 * @author 605162215@qq.com
 *
 * @date 2017年9月21日 下午3:03:48
 */
public class ControllerAccessInfo {
	private Class<?> controllerClazz;
	private List<MethodAccessInfo> methodInfos;
	public Class<?> getControllerClazz() {
		return controllerClazz;
	}
	public void setControllerClazz(Class<?> controllerClazz) {
		this.controllerClazz = controllerClazz;
	}
	public List<MethodAccessInfo> getMethodInfos() {
		return methodInfos;
	}
	public void setMethodInfos(List<MethodAccessInfo> methodInfos) {
		this.methodInfos = methodInfos;
	}
}
