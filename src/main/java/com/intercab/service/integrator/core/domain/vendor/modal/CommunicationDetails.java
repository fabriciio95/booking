package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class CommunicationDetails implements Serializable {
	private static final long serialVersionUID = 8421348226742394455L;

	private long version;
	private String communicationType;
	private String communicationValue;
	private boolean isPreferred;

	public CommunicationDetails() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getCommunicationType() {
		return communicationType;
	}

	public void setCommunicationType(String communicationType) {
		this.communicationType = communicationType;
	}

	public String getCommunicationValue() {
		return communicationValue;
	}

	public void setCommunicationValue(String communicationValue) {
		this.communicationValue = communicationValue;
	}

	public boolean isPreferred() {
		return isPreferred;
	}

	public void setPreferred(boolean preferred) {
		isPreferred = preferred;
	}
}