package com.intercab.service.integrator.core.domain.log.modal;

import java.io.Serializable;
import java.util.Date;

public class CountryLog implements Serializable {
	private static final long serialVersionUID = -5365944178111670675L;

	private Long countryId;
	private Date startDate;
	private Date endDate;
	private String action;
	private Object data;
	private Object error;
	private String status;

	public CountryLog(Date startDate, String strData) {
		this.startDate = startDate;
		this.data = strData;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
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