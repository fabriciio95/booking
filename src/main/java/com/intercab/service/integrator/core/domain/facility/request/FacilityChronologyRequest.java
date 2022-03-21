package com.intercab.service.integrator.core.domain.facility.request;

import java.io.Serializable;
import java.util.Date;

public class FacilityChronologyRequest implements Serializable {
	private static final long serialVersionUID = 3438599582402368122L;

	private Date effectiveFrom;
	private Date effectiveTo;
	private FacilityNamingRequest naming;
	private FacilityLogHierarchyRequest logHierarchy;
	private FacilityPartnerRequest partner;
	private Long version;

	public FacilityNamingRequest getNaming() {
		return naming;
	}

	public void setNaming(FacilityNamingRequest naming) {
		this.naming = naming;
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}

	public FacilityLogHierarchyRequest getLogHierarchy() {
		return logHierarchy;
	}

	public void setLogHierarchy(FacilityLogHierarchyRequest logHierarchy) {
		this.logHierarchy = logHierarchy;
	}

	public FacilityPartnerRequest getPartner() {
		return partner;
	}

	public void setPartner(FacilityPartnerRequest partner) {
		this.partner = partner;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}