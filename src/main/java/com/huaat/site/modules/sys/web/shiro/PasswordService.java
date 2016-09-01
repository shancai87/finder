package com.huaat.site.modules.sys.web.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.huaat.site.modules.sys.entity.User;

public class PasswordService extends HashedCredentialsMatcher {

	RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	Cache<String, AtomicInteger> passwordRetryCache;

	public PasswordService(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			passwordRetryCache.remove(username);
		}
		return matches;
	}

	public void encryptPassword(User user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		DefaultHashService hashService = new DefaultHashService();
		HashRequest.Builder requestBuilder = new HashRequest.Builder();
		requestBuilder.setSource(ByteSource.Util.bytes(user.getPwd()));
		requestBuilder.setSalt(user.getAccount() + user.getSalt());
		requestBuilder.setAlgorithmName(getHashAlgorithmName());
		requestBuilder.setIterations(getHashIterations());
		user.setPwd(hashService.computeHash(requestBuilder.build()).toHex());
	}

	public boolean isMatch(User user, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), password);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
		info.setPrincipals(new SimplePrincipalCollection(user, user.getAccount()));
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getAccount() + user.getSalt()));
		info.setCredentials(user.getPwd());
		return super.doCredentialsMatch(token, info);
	}

}
