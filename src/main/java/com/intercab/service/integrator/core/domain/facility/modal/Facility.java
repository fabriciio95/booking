package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;
import java.util.List;

public class Facility implements Serializable {
	private static final long serialVersionUID = -5116596156174217969L;

	private List<FacilityChronology> chronology;
	private FacilityDetails facilityDetails;
	private List<FacilityStatus> facilityStatus;
	private String code;
	private boolean customerFacility;
	private Long version;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<FacilityChronology> getChronology() {
		return chronology;
	}

	public void setChronology(List<FacilityChronology> chronology) {
		this.chronology = chronology;
	}

	public FacilityDetails getFacilityDetails() {
		return facilityDetails;
	}

	public void setFacilityDetails(FacilityDetails facilityDetails) {
		this.facilityDetails = facilityDetails;
	}

	public List<FacilityStatus> getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(List<FacilityStatus> facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public boolean isCustomerFacility() {
		return customerFacility;
	}

	public void setCustomerFacility(boolean customerFacility) {
		this.customerFacility = customerFacility;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}