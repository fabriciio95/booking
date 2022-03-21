package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.facility.enums.DomainDayType;
import com.intercab.service.integrator.core.domain.facility.enums.DomainFacilityHourType;

public class FacilityHour implements Serializable {
	private static final long serialVersionUID = -3283218872866896591L;

	private DomainFacilityHourType facilityHourType;
	private DomainDayType dayType;
	private String fromTime;
	private String toTime;
	private Long version;

	public DomainFacilityHourType getType() {
		return facilityHourType;
	}

	public void setType(DomainFacilityHourType facilityHourType) {
		this.facilityHourType = facilityHourType;
	}

	public DomainFacilityHourType getFacilityHourType() {
		return facilityHourType;
	}

	public void setFacilityHourType(DomainFacilityHourType facilityHourType) {
		this.facilityHourType = facilityHourType;
	}

	public DomainDayType getDayType() {
		return dayType;
	}

	public void setDayType(DomainDayType dayType) {
		this.dayType = dayType;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}