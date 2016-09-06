package com.shancai.finder.common.message;

public class AjaxMessage {

	boolean success = true;
	int code;
	String message;
	Object data;

	public AjaxMessage() {
	}

	public AjaxMessage(boolean success, String message) {
		setSuccess(success);
		setMessage(message);
	}

	public boolean isSuccess() {
		return success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
