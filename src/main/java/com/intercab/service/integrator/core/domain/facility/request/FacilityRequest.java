package com.intercab.service.integrator.core.domain.facility.request;

import java.io.Serializable;
import java.util.List;

public class FacilityRequest implements Serializable {
	private static final long serialVersionUID = 1897805738235900116L;

	private String globeFacilityCode;
	private Boolean globeCustomerFacility;
	private List<FacilityChronologyRequest> chronologies;
	private FacilityDetailsRequest facilityDetails;
	private List<FacilityStatusRequest> facilityStatus;
	private Long version;

	public String getGlobeFacilityCode() {
		return globeFacilityCode;
	}
	public void setGlobeFacilityCode(String globeFacilityCode) {
		this.globeFacilityCode = globeFacilityCode;
	}

	public Boolean getGlobeCustomerFacility() {
		return globeCustomerFacility;
	}

	public void setGlobeCustomerFacility(Boolean globeCustomerFacility) {
		this.globeCustomerFacility = globeCustomerFacility;
	}

	public List<FacilityChronologyRequest> getChronologies() {
		return chronologies;
	}

	public void setChronologies(List<FacilityChronologyRequest> chronologies) {
		this.chronologies = chronologies;
	}

	public FacilityDetailsRequest getFacilityDetails() {
		return facilityDetails;
	}

	public void setFacilityDetails(FacilityDetailsRequest facilityDetails) {
		this.facilityDetails = facilityDetails;
	}

	public List<FacilityStatusRequest> getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(List<FacilityStatusRequest> facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}