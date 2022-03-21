package com.intercab.service.integrator.core.domain.facility.modal;

import java.io.Serializable;

public class FacilityLogHierarchy implements Serializable {
	private static final long serialVersionUID = 1584538201162699015L;
	
	private String parent;

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
}