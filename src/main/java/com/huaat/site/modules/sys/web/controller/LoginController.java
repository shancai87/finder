package com.huaat.site.modules.sys.web.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import com.huaat.site.common.AjaxMessage;

@RestController
@RequestMapping("sys")
public class LoginController {

	public static final String KAPTCHA_SESSION_KEY = "kaptcha_session_key";

	@GetMapping(value = "captcha.jpg")
	public void captcha(HttpServletRequest request, HttpServletResponse response) {
		ImageIO.setUseCache(false);
		Properties props = new Properties();
		props.put("kaptcha.textproducer.font.color", "black");
		props.put("kaptcha.textproducer.char.space", "5");
		Config config = new Config(props);
		Producer kaptchaProducer = config.getProducerImpl();

		String capText = kaptchaProducer.createText();
		BufferedImage bi = kaptchaProducer.createImage(capText);
		Subject subject = SecurityUtils.getSubject();
		subject.getSession().setAttribute(KAPTCHA_SESSION_KEY, capText);
		try {
			ServletOutputStream out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
		} catch (IOException e) {
		}
	}

	@PostMapping(value = "login")
	public AjaxMessage login(String username, String password, String remenberMe, String captcha) {
		try {
			Subject subject = SecurityUtils.getSubject();
			String sessionCaptcha = (String) subject.getSession().getAttribute(KAPTCHA_SESSION_KEY);
			if (sessionCaptcha == null || !sessionCaptcha.equals(captcha)) {
				return new AjaxMessage(false, "请输入正确的验证码");
			}
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
