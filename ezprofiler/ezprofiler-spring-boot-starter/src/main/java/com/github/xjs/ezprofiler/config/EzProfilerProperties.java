package com.github.xjs.ezprofiler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年4月12日 下午2:59:56<br/>
 */
@Component
public class EzProfilerProperties {
	
		@Value("${ezprofiler.enableBasic}")
		private boolean enableBasic = true;
		
		@Value("${ezprofiler.username}")
		private String username = "ezprofiler-admin";
		
		@Value("${ezprofiler.password}")
		private String password = "ezprofiler-admin";
		
		@Value("${ezprofiler.url}")
		private String url = "/profiler";
		
		@Value("${ezprofiler.basepackage}")
		private String basePackage="com";
		
		public boolean isEnableBasic() {
			return enableBasic;
		}
		public void setEnableBasic(boolean enableBasic) {
			this.enableBasic = enableBasic;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getBasePackage() {
			return basePackage;
		}
		public void setBasePackage(String basePackage) {
			this.basePackage = basePackage;
		}
}
