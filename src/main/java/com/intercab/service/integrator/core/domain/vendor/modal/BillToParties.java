package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class BillToParties implements Serializable {
	private static final long serialVersionUID = 8421348226794009842L;

	private String btPPartnerNumber;
	private boolean isDefault;
	private String cityNumber;
	private String effectiveDate;
	private String expiryDate;

	public BillToParties() {
	}

	public String getBtPPartnerNumber() {
		return btPPartnerNumber;
	}

	public void setBtPPartnerNumber(String btPPartnerNumber) {
		this.btPPartnerNumber = btPPartnerNumber;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean aDefault) {
		isDefault = aDefault;
	}

	public String getCityNumber() {
		return cityNumber;
	}

	public void setCityNumber(String cityNumber) {
		this.cityNumber = cityNumber;
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
}