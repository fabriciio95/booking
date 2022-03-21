package com.intercab.service.integrator.core.domain.log.modal;

import java.util.Date;

public class LocationLog extends CommonLog {
	private static final long serialVersionUID = -8014975000227929354L;
	
	private Long locationId;
	private Date startDate;
	private Date endDate;
	
	public LocationLog(Date startDate, String strData) {
		this.startDate = startDate;
		this.setData(startDate);
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
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