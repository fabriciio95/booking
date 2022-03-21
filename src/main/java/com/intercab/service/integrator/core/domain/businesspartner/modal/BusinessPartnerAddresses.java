package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerAddresses implements Serializable {
	private static final long serialVersionUID = -366697553320756460L;

	private String functionAddressType;
	private boolean isCustomer;
	private boolean isDefaultAddress;
	private boolean isVendor;
	private BusinessPartnerAddressesOverviews[] addressOverviews;
	private String[] formattedAddress;
	private BusinessPartnerContacts[] contacts;
	private BusinessPartnerReferencingFiAccounts[] referencingFiAccounts;
	private Long version;

	public String getFunctionAddressType() {
		return functionAddressType;
	}

	public void setFunctionAddressType(String functionAddressType) {
		this.functionAddressType = functionAddressType;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	public boolean isDefaultAddress() {
		return isDefaultAddress;
	}

	public void setDefaultAddress(boolean isDefaultAddress) {
		this.isDefaultAddress = isDefaultAddress;
	}

	public boolean isVendor() {
		return isVendor;
	}

	public void setVendor(boolean isVendor) {
		this.isVendor = isVendor;
	}

	public BusinessPartnerAddressesOverviews[] getAddressOverviews() {
		return addressOverviews;
	}

	public void setAddressOverviews(BusinessPartnerAddressesOverviews[] addressOverviews) {
		this.addressOverviews = addressOverviews;
	}

	public String[] getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String[] formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public BusinessPartnerContacts[] getContacts() {
		return contacts;
	}

	public void setContacts(BusinessPartnerContacts[] contacts) {
		this.contacts = contacts;
	}

	public BusinessPartnerReferencingFiAccounts[] getReferencingFiAccounts() {
		return referencingFiAccounts;
	}

	public void setReferencingFiAccounts(BusinessPartnerReferencingFiAccounts[] referencingFiAccounts) {
		this.referencingFiAccounts = referencingFiAccounts;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}