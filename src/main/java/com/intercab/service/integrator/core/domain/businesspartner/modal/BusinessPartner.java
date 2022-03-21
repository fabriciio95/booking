package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;
import java.util.List;

public class BusinessPartner implements Serializable {
	private static final long serialVersionUID = 4775289075927446068L;

	private String code;
	private Long version;
	private String partnerShortName;
	private String alias;
	private String remark;
	private boolean isEquipmentCustomer;
	private boolean isKYCChecked;
	private String mainTelephoneNo;
	private String localLanguage;
	private BusinessPartnerNames[] partnerNames;
	private BusinessBoycottStatuses[] boycottStatuses;
	private List<BusinessPartnerRoles> partnerRoles;
	private BusinessPartnerTaxNumbers[] taxNumbers;
	private BusinessPartnerAddresses[] addresses;
	private BusinessPartnerFiAccounts[] fiAccounts;
	private BusinessPartnerEcommerceChannels[] ecommerceChannels;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getPartnerShortName() {
		return partnerShortName;
	}

	public void setPartnerShortName(String partnerShortName) {
		this.partnerShortName = partnerShortName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isEquipmentCustomer() {
		return isEquipmentCustomer;
	}

	public void setEquipmentCustomer(boolean isEquipmentCustomer) {
		this.isEquipmentCustomer = isEquipmentCustomer;
	}

	public boolean isKYCChecked() {
		return isKYCChecked;
	}

	public void setKYCChecked(boolean isKYCChecked) {
		this.isKYCChecked = isKYCChecked;
	}

	public String getMainTelephoneNo() {
		return mainTelephoneNo;
	}

	public void setMainTelephoneNo(String mainTelephoneNo) {
		this.mainTelephoneNo = mainTelephoneNo;
	}

	public String getLocalLanguage() {
		return localLanguage;
	}

	public void setLocalLanguage(String localLanguage) {
		this.localLanguage = localLanguage;
	}

	public BusinessPartnerNames[] getPartnerNames() {
		return partnerNames;
	}

	public void setPartnerNames(BusinessPartnerNames[] partnerNames) {
		this.partnerNames = partnerNames;
	}

	public BusinessBoycottStatuses[] getBoycottStatuses() {
		return boycottStatuses;
	}

	public void setBoycottStatuses(BusinessBoycottStatuses[] boycottStatuses) {
		this.boycottStatuses = boycottStatuses;
	}

	public List<BusinessPartnerRoles>  getPartnerRoles() {
		return partnerRoles;
	}

	public void setPartnerRoles(List<BusinessPartnerRoles> partnerRoles) {
		this.partnerRoles = partnerRoles;
	}

	public BusinessPartnerTaxNumbers[] getTaxNumbers() {
		return taxNumbers;
	}

	public void setTaxNumbers(BusinessPartnerTaxNumbers[] taxNumbers) {
		this.taxNumbers = taxNumbers;
	}

	public BusinessPartnerAddresses[] getAddresses() {
		return addresses;
	}

	public void setAddresses(BusinessPartnerAddresses[] addresses) {
		this.addresses = addresses;
	}

	public BusinessPartnerFiAccounts[] getFiAccounts() {
		return fiAccounts;
	}

	public void setFiAccounts(BusinessPartnerFiAccounts[] fiAccounts) {
		this.fiAccounts = fiAccounts;
	}

	public BusinessPartnerEcommerceChannels[] getEcommerceChannels() {
		return ecommerceChannels;
	}

	public void setEcommerceChannels(BusinessPartnerEcommerceChannels[] ecommerceChannels) {
		this.ecommerceChannels = ecommerceChannels;
	}
}