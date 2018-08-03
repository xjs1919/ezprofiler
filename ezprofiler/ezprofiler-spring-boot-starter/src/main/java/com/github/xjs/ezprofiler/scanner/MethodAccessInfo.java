package com.github.xjs.ezprofiler.scanner;

import java.util.Date;

/**
 * @author 605162215@qq.com
 *
 * @date 2017年9月21日 下午3:30:10
 */
public class MethodAccessInfo {
	private String method;
	private String uri;
	//历史的调用信息
	private long invokeCount;
	private long okCount;
	private long errorCount;
	private long minMills;
	private long maxMills;
	private long avgMills;
	private Date maxInvokeAt;
	//当天的调用信息
	private long todayCount;
	private long todayOkCount;
	private long todayErrorCount;
	private long todayMinMills;
	private long todayMaxMills;
	private long todayAvgMills;
	private Date todayMaxInvokeAt;
	//上一次的调用信息
	private long lastMills;
	private Date lastInvokeAt;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public long getInvokeCount() {
		return invokeCount;
	}
	public void setInvokeCount(long invokeCount) {
		this.invokeCount = invokeCount;
	}
	public long getOkCount() {
		return okCount;
	}
	public void setOkCount(long okCount) {
		this.okCount = okCount;
	}
	public long getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(long errorCount) {
		this.errorCount = errorCount;
	}
	public long getMinMills() {
		return minMills;
	}
	public void setMinMills(long minMills) {
		this.minMills = minMills;
	}
	public long getMaxMills() {
		return maxMills;
	}
	public void setMaxMills(long maxMills) {
		this.maxMills = maxMills;
	}
	public long getAvgMills() {
		return avgMills;
	}
	public void setAvgMills(long avgMills) {
		this.avgMills = avgMills;
	}
	public long getTodayCount() {
		return todayCount;
	}
	public void setTodayCount(long todayCount) {
		this.todayCount = todayCount;
	}
	public long getTodayOkCount() {
		return todayOkCount;
	}
	public void setTodayOkCount(long todayOkCount) {
		this.todayOkCount = todayOkCount;
	}
	public long getTodayErrorCount() {
		return todayErrorCount;
	}
	public void setTodayErrorCount(long todayErrorCount) {
		this.todayErrorCount = todayErrorCount;
	}
	public long getTodayMinMills() {
		return todayMinMills;
	}
	public void setTodayMinMills(long todayMinMills) {
		this.todayMinMills = todayMinMills;
	}
	public long getTodayMaxMills() {
		return todayMaxMills;
	}
	public void setTodayMaxMills(long todayMaxMills) {
		this.todayMaxMills = todayMaxMills;
	}
	public long getTodayAvgMills() {
		return todayAvgMills;
	}
	public void setTodayAvgMills(long todayAvgMills) {
		this.todayAvgMills = todayAvgMills;
	}
	public long getLastMills() {
		return lastMills;
	}
	public void setLastMills(long lastMills) {
		this.lastMills = lastMills;
	}
	public Date getLastInvokeAt() {
		return lastInvokeAt;
	}
	public void setLastInvokeAt(Date lastInvokeTime) {
		this.lastInvokeAt = lastInvokeTime;
	}
	public Date getMaxInvokeAt() {
		return maxInvokeAt;
	}
	public void setMaxInvokeAt(Date maxInvokeAt) {
		this.maxInvokeAt = maxInvokeAt;
	}
	public Date getTodayMaxInvokeAt() {
		return todayMaxInvokeAt;
	}
	public void setTodayMaxInvokeAt(Date todayMaxInvokeAt) {
		this.todayMaxInvokeAt = todayMaxInvokeAt;
	}
}
