package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

import com.intercab.service.integrator.core.domain.ProcessDataRequest;

public class BusinessPartnerRequest implements Serializable {
	private static final long serialVersionUID = 2438912095876854617L;

	private Long businessPartnerId;
	private String companyName;
	private String stateRegistration;
	private boolean stateRegistrationExempt;
	private String taxId;
	private String personType;
	private BusinessPartnerAddressesRequest[] facilityList;
	private Long[] vendorBusinessList;
	private ProcessDataRequest processData;
	private BusinessPartnerStatus status;
	private String globeStatusDescription;

	public Long getBusinessPartnerId() {
		return businessPartnerId;
	}

	public void setBusinessPartnerId(Long businessPartnerId) {
		this.businessPartnerId = businessPartnerId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setStateRegistration(String stateRegistration) {
		this.stateRegistration = stateRegistration;
	}

	public boolean isStateRegistrationExempt() {
		return stateRegistrationExempt;
	}

	public void setStateRegistrationExempt(boolean stateRegistrationExempt) {
		this.stateRegistrationExempt = stateRegistrationExempt;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public BusinessPartnerAddressesRequest[] getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(BusinessPartnerAddressesRequest[] facilityList) {
		this.facilityList = facilityList;
	}

	public Long[] getVendorBusinessList() {
		return vendorBusinessList;
	}

	public void setVendorBusinessList(Long[] vendorBusinessList) {
		this.vendorBusinessList = vendorBusinessList;
	}

	public ProcessDataRequest getProcessData() {
		return processData;
	}

	public void setProcessData(ProcessDataRequest processData) {
		this.processData = processData;
	}

	public BusinessPartnerStatus getStatus() {
		return status;
	}

	public void setStatus(BusinessPartnerStatus status) {
		this.status = status;
	}

	public String getGlobeStatusDescription() {
		return globeStatusDescription;
	}

	public void setGlobeStatusDescription(String globeStatusDescription) {
		this.globeStatusDescription = globeStatusDescription;
	}
}