package com.shancai.finder.modules.sys.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sys_user")
public class User extends BaseEntity {

	private static final long serialVersionUID = 1L;

	// 归属公司
	@JoinColumn
	@ManyToOne
	Org org;

	// 归属部门
	@JoinColumn
	@ManyToOne
	Org office;

	// 账号
	@Column(nullable = false, unique = true, length = 20)
	String account;

	// 密码
	@JsonIgnore
	@Column(length = 64)
	String pwd;

	// 盐
	@JsonIgnore
	@Column(length = 64)
	String salt;

	// 工号
	@Column(length = 20)
	String no;

	// 姓名
	@Column(length = 20)
	String name;

	// 性别
	@Column(length = 10)
	String gender;

	// 邮箱
	@Column(length = 50)
	String email;

	// 电话
	@Column(length = 18)
	String phone;

	// 用户类型
	@Column(length = 10)
	String userType;

	// 用户头像
	@Column(length = 200)
	String photo;

	// 最后登陆IP
	@Column(length = 20)
	String loginIp;

	// 最后登陆时间
	@Column
	Date loginDate;

	// 是否锁定
	@Column
	boolean locked = false;

	@JoinTable(name = "sys_user_role")
	@ManyToMany
	List<Role> role = new ArrayList<>();

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public Org getOffice() {
		return office;
	}

	public void setOffice(Org office) {
		this.office = office;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

}
