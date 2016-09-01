package com.huaat.site.modules.sys.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseTreeEntity<T> extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@JoinColumn
	@ManyToOne
	public T parent;

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

}
