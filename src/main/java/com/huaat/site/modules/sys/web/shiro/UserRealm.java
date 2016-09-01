package com.huaat.site.modules.sys.web.shiro;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.huaat.site.modules.sys.entity.Role;
import com.huaat.site.modules.sys.entity.User;
import com.huaat.site.modules.sys.repository.UserRepository;

public class UserRealm extends AuthorizingRealm {

	@Resource
	UserRepository userRepo;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		List<Role> roleList = userRepo.findByAccount(username).getRole();
		if (roleList != null) {
			Set<String> roles = roleList.stream().map(r -> r.getEnname()).collect((Collectors.toSet()));
			authorizationInfo.setRoles(roles);
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userRepo.findByAccount(username);
		if (user == null) {
			throw new UnknownAccountException();
		}

		if (user.isLocked()) {
			throw new LockedAccountException();
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(//
				user.getAccount(), //
				user.getPwd(), //
				ByteSource.Util.bytes(user.getAccount() + user.getSalt()), //
				getName());
		return authenticationInfo;
	}

}
