package com.huaat.site.modules.sys.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huaat.site.modules.sys.entity.User;
import com.huaat.site.modules.sys.repository.UserRepository;
import com.huaat.site.modules.sys.web.shiro.PasswordService;

@Service
@Transactional
public class UserService {

	@Resource
	PasswordService passwordService;

	@Resource
	UserRepository userRepo;

	public void initization() {
		User user = new User();
		user.setAccount("admin");
		user.setName("超级管理员");
		user.setPwd("123456");
		passwordService.encryptPassword(user);
		userRepo.save(user);
	}

	public void save(User user) {
		userRepo.save(user);
	}

}
