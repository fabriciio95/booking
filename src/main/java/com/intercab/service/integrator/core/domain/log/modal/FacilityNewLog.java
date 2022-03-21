package com.intercab.service.integrator.core.domain.log.modal;

import java.util.Date;

public class FacilityNewLog extends CommonLog {
	private static final long serialVersionUID = -674517525707917463L;
	
	private Long facilityId;
	private Date startDate;
	private Date endDate;

	public FacilityNewLog(Date startDate, String strData) {
		this.startDate = startDate;
		this.setData(startDate);
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
}