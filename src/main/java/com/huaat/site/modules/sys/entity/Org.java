package com.huaat.site.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sys_org")
public class Org extends BaseTreeEntity<Org> {

	private static final long serialVersionUID = 1L;

	@Column(length = 50)
	String name;// 名称

	@Column
	Long sort;// 排序

	@JoinColumn
	@ManyToOne
	Region region;// 归属区域

	@Column(length = 20)
	String code; // 区域编码

	@Column
	String type;// 机构类型

	@Column
	int grade;// 机构等级

	@Column
	String address;// 联系地址

	@Column(length = 18)
	String zipCode;// 邮政编码

	@Column(length = 20)
	String master;// 负责人

	@Column(length = 20)
	String tel;// 电话

	@Column(length = 20)
	String fax;// 传真

	@Column(length = 50)
	String email;// 邮箱

	@Column
	boolean useable;// 是否启用

	@Column(length = 20)
	String primaryPerson;// 主负责人

	@Column(length = 20)
	String deputyPerson;// 副负责人

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

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getUseable() {
		return useable;
	}

	public void setUseable(boolean useable) {
		this.useable = useable;
	}

	public String getPrimaryPerson() {
		return primaryPerson;
	}

	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson;
	}

	public String getDeputyPerson() {
		return deputyPerson;
	}

	public void setDeputyPerson(String deputyPerson) {
		this.deputyPerson = deputyPerson;
	}

}
