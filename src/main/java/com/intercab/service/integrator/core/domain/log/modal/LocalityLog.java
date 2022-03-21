package com.intercab.service.integrator.core.domain.log.modal;

import java.io.Serializable;
import java.util.Date;

public class LocalityLog implements Serializable {
	private static final long serialVersionUID = -4870092808520318596L;

	private Long code;
	private Date startDate;
	private Date endDate;
	private String action;
	private Object data;
	private Object error;
	private String status;

	public LocalityLog(Date startDate, String changeType, Object data) {
		this.startDate = startDate;
		this.action = changeType;
		this.data = data;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

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