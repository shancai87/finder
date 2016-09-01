package com.shancai.finder.modules.sys.repository;

import org.springframework.stereotype.Repository;

import com.shancai.finder.modules.sys.entity.User;

@Repository
public interface UserRepository extends AbstractRepository<User> {

	public User findByAccount(String account);

}
