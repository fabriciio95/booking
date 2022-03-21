package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class Contacts implements Serializable {
	private static final long serialVersionUID = 8421348226742393344L;

	private long version;
	private String businessFunction;
	private String personType;
	private String personTitle;
	private String firstName;
	private String lastName;
	private String effectiveDate;
	private String expiryDate;
	private CommunicationDetails[] CommunicationDetails;

	public Contacts() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getBusinessFunction() {
		return businessFunction;
	}

	public void setBusinessFunction(String businessFunction) {
		this.businessFunction = businessFunction;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPersonTitle() {
		return personTitle;
	}

	public void setPersonTitle(String personTitle) {
		this.personTitle = personTitle;
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

	public CommunicationDetails[] getCommunicationDetails() {
		return CommunicationDetails;
	}

	public void setCommunicationDetails(CommunicationDetails[] communicationDetails) {
		CommunicationDetails = communicationDetails;
	}
}