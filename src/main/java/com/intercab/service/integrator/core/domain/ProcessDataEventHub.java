package com.intercab.service.integrator.core.domain;

import java.io.Serializable;

public class ProcessDataEventHub implements Serializable {
	private static final long serialVersionUID = -3605352910062025838L;

	private String changeType;
	private String versionNumber;

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
}