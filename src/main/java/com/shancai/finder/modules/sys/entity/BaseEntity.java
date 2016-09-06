package com.shancai.finder.modules.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
public abstract class BaseEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	// 创建人
	@JoinColumn
	@ManyToOne
	@CreatedBy
	User createBy;

	// 创建时间
	@Column
	@CreatedDate
	Date createDate;

	// 修改人
	@JoinColumn
	@ManyToOne
	@LastModifiedBy
	User updateBy;

	// 修改时间
	@Column
	@LastModifiedDate
	Date updateDate;

	// 备注
	@Column
	String remark;

	// 是否已删除
	@Column
	boolean isDeleted = false;

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
