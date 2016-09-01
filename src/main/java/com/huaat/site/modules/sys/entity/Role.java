package com.huaat.site.modules.sys.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@JoinColumn
	@ManyToOne
	Org org;// 归属机构

	@Column(length = 36)
	String name;// 角色名称

	@Column(length = 50)
	String enname;// 英文名称

	@Column(length = 10)
	String roleType;// 角色类型

	@Column(length = 100)
	String dataScope;// 数据范围

	@Column
	String isSys;// 是否系统数据

	@Column
	boolean useable;// 是否可用

	@JoinTable(name = "sys_role_menu")
	@ManyToMany
	List<Menu> menu = new ArrayList<>();

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnname() {
		return enname;
	}

	public void setEnname(String enname) {
		this.enname = enname;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}

	public String getIsSys() {
		return isSys;
	}

	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}

	public boolean getUseable() {
		return useable;
	}

	public void setUseable(boolean useable) {
		this.useable = useable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
