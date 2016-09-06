package com.shancai.finder.modules.sys.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RestController;

import com.shancai.finder.common.message.AjaxMessage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("sys")
public class LoginController {

	@PostMapping(value = "login")
	public AjaxMessage login(String username, String password, String remenberMe, String captcha) {
		try {
			Subject subject = SecurityUtils.getSubject();
			if (!subject.isAuthenticated() || !subject.getPrincipal().equals(username)) {
				subject.logout();
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				token.setRememberMe("1".equals(remenberMe));
				subject.login(token);
			}
			return new AjaxMessage();
		} catch (Exception ex) {
			if (ex instanceof UnknownAccountException) {
				return new AjaxMessage(false, "用户名不存在");
			} else if (ex instanceof LockedAccountException) {
				return new AjaxMessage(false, "账户已锁定");
			} else if (ex instanceof IncorrectCredentialsException) {
				return new AjaxMessage(false, "用户名/密码不正确");
			} else if (ex instanceof ExcessiveAttemptsException) {
				return new AjaxMessage(false, "超过5次登录");
			} else {
				return new AjaxMessage(false, ex.getMessage());
			}
		}
	}

	@GetMapping(value = "logout")
	public AjaxMessage logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return new AjaxMessage();
	}

}
