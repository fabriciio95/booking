package com.intercab.service.integrator.core.domain.log.modal;

import java.io.Serializable;

public class CommonLog implements Serializable{
	private static final long serialVersionUID = -8463225146871150459L;
	
	private String action;
	private Object data;
	private Object error;
	private String status;
	
	public String getAction() {
		return action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getError() {
		return error;
	}
	
	public void setError(Object error) {
		this.error = error;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
