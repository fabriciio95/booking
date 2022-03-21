package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerCommunicationDetails implements Serializable {
	private static final long serialVersionUID = 8288630342741064367L;

	private String communicationType;
	private String communicationValue;
	private boolean isPreferred;
	private Long version;

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

	public void setPreferred(boolean isPreferred) {
		this.isPreferred = isPreferred;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}