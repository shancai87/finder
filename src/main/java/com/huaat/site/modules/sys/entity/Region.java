package com.huaat.site.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_region")
public class Region extends BaseTreeEntity<Region> {

	private static final long serialVersionUID = 1L;

	@Column(length = 30)
	String name; // 名称

	@Column
	Long sort;// 排序

	@Column(length = 20)
	String code;// 区域编码

	@Column(length = 10)
	String type;// 区域类型

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
