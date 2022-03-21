package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;

public class FacilityChronology implements Serializable {
	private static final long serialVersionUID = 3438599582402368122L;

	private String effectiveFrom;
	private String effectiveTo;
	private FacilityNaming naming;
	private FacilityLogHierarchy logHierarchy;
	private FacilityPartner partner;
	private Long version;

	public FacilityNaming getNaming() {
		return naming;
	}

	public void setNaming(FacilityNaming naming) {
		this.naming = naming;
	}

	public FacilityLogHierarchy getLogHierarchy() {
		return logHierarchy;
	}

	public void setLogHierarchy(FacilityLogHierarchy logHierarchy) {
		this.logHierarchy = logHierarchy;
	}

	public FacilityPartner getPartner() {
		return partner;
	}

	public void setPartner(FacilityPartner partner) {
		this.partner = partner;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public String getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(String effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
}