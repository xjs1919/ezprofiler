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
	//最近一天的调用信息
	private long lastDayCount;
	private long lastDayOkCount;
	private long lastDayErrorCount;
	private long lastDayMinMills;
	private long lastDayMaxMills;
	private long lastDayAvgMills;
	private Date lastDayMaxInvokeAt;
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
	public Date getMaxInvokeAt() {
		return maxInvokeAt;
	}
	public void setMaxInvokeAt(Date maxInvokeAt) {
		this.maxInvokeAt = maxInvokeAt;
	}
	public long getLastDayCount() {
		return lastDayCount;
	}
	public void setLastDayCount(long lastDayCount) {
		this.lastDayCount = lastDayCount;
	}
	public long getLastDayOkCount() {
		return lastDayOkCount;
	}
	public void setLastDayOkCount(long lastDayOkCount) {
		this.lastDayOkCount = lastDayOkCount;
	}
	public long getLastDayErrorCount() {
		return lastDayErrorCount;
	}
	public void setLastDayErrorCount(long lastDayErrorCount) {
		this.lastDayErrorCount = lastDayErrorCount;
	}
	public long getLastDayMinMills() {
		return lastDayMinMills;
	}
	public void setLastDayMinMills(long lastDayMinMills) {
		this.lastDayMinMills = lastDayMinMills;
	}
	public long getLastDayMaxMills() {
		return lastDayMaxMills;
	}
	public void setLastDayMaxMills(long lastDayMaxMills) {
		this.lastDayMaxMills = lastDayMaxMills;
	}
	public long getLastDayAvgMills() {
		return lastDayAvgMills;
	}
	public void setLastDayAvgMills(long lastDayAvgMills) {
		this.lastDayAvgMills = lastDayAvgMills;
	}
	public Date getLastDayMaxInvokeAt() {
		return lastDayMaxInvokeAt;
	}
	public void setLastDayMaxInvokeAt(Date lastDayMaxInvokeAt) {
		this.lastDayMaxInvokeAt = lastDayMaxInvokeAt;
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
	public void setLastInvokeAt(Date lastInvokeAt) {
		this.lastInvokeAt = lastInvokeAt;
	}
}
