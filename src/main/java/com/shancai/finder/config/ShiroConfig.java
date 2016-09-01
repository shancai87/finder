package com.shancai.finder.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shancai.finder.modules.sys.web.shiro.PasswordService;
import com.shancai.finder.modules.sys.web.shiro.UserRealm;

@Configuration
public class ShiroConfig {

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean cacheManager = new EhCacheManagerFactoryBean();
		cacheManager.setShared(true);
		return cacheManager;
	}

	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return cacheManager;
	}

	@Bean
	public UserRealm realm() {
		UserRealm realm = new UserRealm();
		realm.setCacheManager(ehCacheManager());
		realm.setCredentialsMatcher(defaultCredentialsMatcher());
		return realm;
	}

	@Bean
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setCacheManager(ehCacheManager());
		securityManager.setRealm(realm());
		return securityManager;
	}

	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager());
		shiroFilter.setLoginUrl("/login.html");
		shiroFilter.setSuccessUrl("/index.html");
		shiroFilter.setUnauthorizedUrl("/error/403.html");
		Map<String, String> filterChains = new HashMap<>();
		filterChains.put("/sys/login", "anon");
		filterChains.put("/sys/captcha.jpg", "anon");
		filterChains.put("/login.html", "anon");
		filterChains.put("/plugins/**", "anon");
		filterChains.put("/themes/**", "anon");
		filterChains.put("/pages/**", "anon");
		filterChains.put("/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChains);
		return shiroFilter;
	}

	@Value(value = "${shiro.password.algorithmName}")
	String algorithmName = "md5";

	@Value(value = "${shiro.password.hashIterations}")
	int hashIterations = 3;

	@Bean
	public PasswordService defaultCredentialsMatcher() {
		PasswordService passwordService = new PasswordService(ehCacheManager());
		passwordService.setHashAlgorithmName(algorithmName);
		passwordService.setHashIterations(hashIterations);
		passwordService.setStoredCredentialsHexEncoded(true);
		return passwordService;
	}

}
