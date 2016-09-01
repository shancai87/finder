package com.huaat.site.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_menu")
public class Menu extends BaseTreeEntity<Menu> {

	private static final long serialVersionUID = 1L;

	@Column(length = 16)
	String name;// 名称

	@Column
	long sort;// 排序

	@Column(length = 120)
	String href;// 链接

	@Column(length = 20)
	String target;// 目标

	@Column(length = 20)
	String icon;// 图标

	@Column(length = 64)
	String permission;// 权限标识

	@Column
	boolean isShow;// 是否在菜单中显示

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
}
