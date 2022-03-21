package com.intercab.service.integrator.core.domain.facility.request;

import java.io.Serializable;

public class FacilityPartnerRequest implements Serializable {
	private static final long serialVersionUID = -3161957258201707091L;
	
	private String partnerCode;
	private String partnerNumber;

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getPartnerNumber() {
		return partnerNumber;
	}

	public void setPartnerNumber(String partnerNumber) {
		this.partnerNumber = partnerNumber;
	}
}