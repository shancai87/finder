package com.huaat.site.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_log")
public class Log extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column(length = 10)
	String type; // 日志类型

	@Column(length = 30)
	String title;// 日志标题

	@Column(length = 15)
	String remoteAddr;// 操作IP地址

	@Column(length = 100)
	String userAgent;// 用户代理

	@Column(length = 200)
	String requestUri;// 请求URI

	@Column(length = 10)
	String method;// 操作方式

	@Column(length = 100)
	String params;// 操作提交的数据

	@Column(length = 1000)
	String exception;// 异常信息

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
