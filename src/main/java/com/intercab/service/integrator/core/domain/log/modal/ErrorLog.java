package com.intercab.service.integrator.core.domain.log.modal;

import java.io.Serializable;

public class ErrorLog implements Serializable {
	private static final long serialVersionUID = -3335026474354654738L;
	private Object error;
	private String status;

	public ErrorLog(String status, Object error) {
		this.status = status;
		this.error = error;
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