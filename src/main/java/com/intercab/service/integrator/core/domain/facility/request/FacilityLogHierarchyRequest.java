package com.intercab.service.integrator.core.domain.facility.request;

import java.io.Serializable;

public class FacilityLogHierarchyRequest implements Serializable {
	private static final long serialVersionUID = 1584538201162699015L;
	
	private String parent;

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
}