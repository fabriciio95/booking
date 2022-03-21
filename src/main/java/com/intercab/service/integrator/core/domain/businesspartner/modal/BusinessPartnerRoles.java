package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;
import java.util.List;

public class BusinessPartnerRoles implements Serializable {
	private static final long serialVersionUID = 5615665042109675928L;

	private boolean overchargedInv;
	private boolean selfBillingVendor;
	private String partnerRole;
	private Long version;
	private List<BusinessPartnerFunctions> partnerFunctions;
	private List<BusinessPartnerCommunicationPreferences> communicationPreferences;
	private List<BusinessPartnerBillToParties> billToParties;
	private List<BusinessPartnerOperatorCodes> operatorCodes;

	public boolean isOverchargedInv() {
		return overchargedInv;
	}

	public void setOverchargedInv(boolean overchargedInv) {
		this.overchargedInv = overchargedInv;
	}

	public boolean isSelfBillingVendor() {
		return selfBillingVendor;
	}

	public void setSelfBillingVendor(boolean selfBillingVendor) {
		this.selfBillingVendor = selfBillingVendor;
	}

	public String getPartnerRole() {
		return partnerRole;
	}

	public void setPartnerRole(String partnerRole) {
		this.partnerRole = partnerRole;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<BusinessPartnerFunctions> getPartnerFunctions() {
		return partnerFunctions;
	}

	public void setPartnerFunctions(List<BusinessPartnerFunctions> partnerFunctions) {
		this.partnerFunctions = partnerFunctions;
	}

	public List<BusinessPartnerCommunicationPreferences> getCommunicationPreferences() {
		return communicationPreferences;
	}

	public void setCommunicationPreferences(List<BusinessPartnerCommunicationPreferences> communicationPreferences) {
		this.communicationPreferences = communicationPreferences;
	}

	public List<BusinessPartnerBillToParties> getBillToParties() {
		return billToParties;
	}

	public void setBillToParties(List<BusinessPartnerBillToParties> billToParties) {
		this.billToParties = billToParties;
	}

	public List<BusinessPartnerOperatorCodes> getOperatorCodes() {
		return operatorCodes;
	}

	public void setOperatorCodes(List<BusinessPartnerOperatorCodes> operatorCodes) {
		this.operatorCodes = operatorCodes;
	}
}