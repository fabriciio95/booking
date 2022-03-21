package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class Addresses implements Serializable {
	private static final long serialVersionUID = 8421348226742393422L;

	private long version;
	private String functionAddressType;
	private boolean isDefaultAddress;
	private boolean isCustomer;
	private boolean isVendor;
	private AddressOverviews[] addressOverviews;
	private String[] formattedAddress;
	private Contacts[] contacts;
	private ReferencingFiAccounts[] referencingFiAccounts;

	public Addresses() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getFunctionAddressType() {
		return functionAddressType;
	}

	public void setFunctionAddressType(String functionAddressType) {
		this.functionAddressType = functionAddressType;
	}

	public boolean isDefaultAddress() {
		return isDefaultAddress;
	}

	public void setDefaultAddress(boolean defaultAddress) {
		isDefaultAddress = defaultAddress;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean customer) {
		isCustomer = customer;
	}

	public boolean isVendor() {
		return isVendor;
	}

	public void setVendor(boolean vendor) {
		isVendor = vendor;
	}

	public AddressOverviews[] getAddressOverviews() {
		return addressOverviews;
	}

	public void setAddressOverviews(AddressOverviews[] addressOverviews) {
		this.addressOverviews = addressOverviews;
	}

	public String[] getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String[] formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public Contacts[] getContacts() {
		return contacts;
	}

	public void setContacts(Contacts[] contacts) {
		this.contacts = contacts;
	}

	public ReferencingFiAccounts[] getReferencingFiAccounts() {
		return referencingFiAccounts;
	}

	public void setReferencingFiAccounts(ReferencingFiAccounts[] referencingFiAccounts) {
		this.referencingFiAccounts = referencingFiAccounts;
	}
}