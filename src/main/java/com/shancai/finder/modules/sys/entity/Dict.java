package com.shancai.finder.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_dict")
public class Dict extends BaseTreeEntity<Dict> {

	private static final long serialVersionUID = 1L;

	@Column(length = 20)
	String value;// 数据值

	@Column(length = 50)
	String label;// 标签名

	@Column(length = 10)
	String type;// 类型

	@Column
	long sort;// 排序（升序）

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}
}
