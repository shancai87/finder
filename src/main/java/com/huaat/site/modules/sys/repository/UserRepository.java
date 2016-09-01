package com.huaat.site.modules.sys.repository;

import org.springframework.stereotype.Repository;

import com.huaat.site.modules.sys.entity.User;

@Repository
public interface UserRepository extends AbstractRepository<User> {

	public User findByAccount(String account);

}
