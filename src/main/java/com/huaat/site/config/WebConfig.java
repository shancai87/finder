package com.huaat.site.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean registShiroFilter() {
		FilterRegistrationBean shiroFilter = new FilterRegistrationBean();
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		proxy.setTargetFilterLifecycle(true);
		shiroFilter.setFilter(proxy);
		shiroFilter.setName("shiroFilter");
		shiroFilter.setUrlPatterns(Arrays.asList("/*"));
		return shiroFilter;
	}
}
