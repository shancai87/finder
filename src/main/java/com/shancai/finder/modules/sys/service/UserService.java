package com.shancai.finder.modules.sys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.shancai.finder.common.validator.Validator;
import com.shancai.finder.modules.sys.entity.User;
import com.shancai.finder.modules.sys.repository.UserRepository;
import com.shancai.finder.modules.sys.web.shiro.PasswordService;
import com.shancai.finder.modules.sys.web.vo.UserFormVo;

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

	@Transactional(readOnly = true)
	public Page<User> listByPage(User user, Pageable page) {
		return userRepo.findAll(new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> preds = new ArrayList<>();
				if (user != null && !StringUtils.isEmpty(user.getAccount())) {
					preds.add(builder.like(root.get("account"), "%" + user.getAccount() + "%"));
				}
				if (user != null && !StringUtils.isEmpty(user.getName())) {
					preds.add(builder.like(root.get("name"), "%" + user.getName() + "%"));
				}
				preds.add(builder.equal(root.get("isDeleted"), false));
				return builder.and(preds.toArray(new Predicate[preds.size()]));
			}
		}, new PageRequest(page.getPageNumber(), page.getPageSize(), new Sort(new Sort.Order("account"))));
	}

	public void createUser(UserFormVo user) {
		if (userRepo.findByAccount(user.getAccount()) != null) {
			new Validator("account", "账号已存在").check();
		}
		User userEntity = user.copyToUser();
		passwordService.encryptPassword(userEntity);
		userRepo.save(userEntity);
	}

	public void updateUser(UserFormVo user) {
		User u = userRepo.findOne(user.getId());
		if (u == null) {
			new Validator("account", "用户不存在").check();
		}
		if (!u.getAccount().equals(user.getAccount()) && userRepo.findByAccount(user.getAccount()) != null) {
			new Validator("account", "账号已存在").check();
		}
		User userEntity = user.copyToUser();
		if (!StringUtils.isEmpty(userEntity.getPwd())) {
			passwordService.encryptPassword(userEntity);
		}
		userRepo.save(userEntity);
	}

	public void deleteUser(String[] ids) {
		Arrays.stream(ids).forEach((id) -> {
			User user = userRepo.findOne(id);
			user.setDeleted(true);
			userRepo.save(user);
		});
	}

}
