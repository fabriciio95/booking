package com.intercab.service.integrator.core.domain.log.modal;

import java.io.Serializable;
import java.util.Date;

public class FacilityLog implements Serializable {
	private static final long serialVersionUID = 5245939178883286292L;

	private Long facilityId;
	private Date startDate;
	private Date endDate;
	private String action;
	private Object data;
	private Object error;
	private String status;

	public FacilityLog(Date startDate, String strData) {
		this.startDate = startDate;
		this.data = strData;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
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