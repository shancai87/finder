package com.shancai.finder.modules.sys.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.shancai.finder.modules.sys.entity.Menu;

@Repository
public interface MenuRepository extends AbstractRepository<Menu> {

	public List<Menu> findByParentId(String parnetId);

}
