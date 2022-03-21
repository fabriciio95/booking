package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerContacts implements Serializable {
	private static final long serialVersionUID = -6379725197197996081L;

	private String businessFunction;
	private String effectiveDate;
	private String expiryDate;
	private String firstName;
	private String lastName;
	private String personTitle;
	private String personType;
	private BusinessPartnerCommunicationDetails[] communicationDetails;
	private Long version;

	public String getBusinessFunction() {
		return businessFunction;
	}

	public void setBusinessFunction(String businessFunction) {
		this.businessFunction = businessFunction;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonTitle() {
		return personTitle;
	}

	public void setPersonTitle(String personTitle) {
		this.personTitle = personTitle;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public BusinessPartnerCommunicationDetails[] getCommunicationDetails() {
		return communicationDetails;
	}

	public void setCommunicationDetails(BusinessPartnerCommunicationDetails[] communicationDetails) {
		this.communicationDetails = communicationDetails;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}