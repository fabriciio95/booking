package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class AddressOverviews implements Serializable {
	private static final long serialVersionUID = 8421348226742393433L;

	private long version;
	private String status;
	private String effectiveDate;
	private String expiryDate;
	private String addressType;
	private String streetName1;
	private String streetName2;
	private String streetName3;
	private String district;
	private String buildingName;
	private String floor;
	private String streetNumber;
	private String postalCode;
	private String cityNumber;
	private String stateNumber;
	private String countryNumber;

	public AddressOverviews() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getStreetName1() {
		return streetName1;
	}

	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
	}

	public String getStreetName2() {
		return streetName2;
	}

	public void setStreetName2(String streetName2) {
		this.streetName2 = streetName2;
	}

	public String getStreetName3() {
		return streetName3;
	}

	public void setStreetName3(String streetName3) {
		this.streetName3 = streetName3;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCityNumber() {
		return cityNumber;
	}

	public void setCityNumber(String cityNumber) {
		this.cityNumber = cityNumber;
	}

	public String getStateNumber() {
		return stateNumber;
	}

	public void setStateNumber(String stateNumber) {
		this.stateNumber = stateNumber;
	}

	public String getCountryNumber() {
		return countryNumber;
	}

	public void setCountryNumber(String countryNumber) {
		this.countryNumber = countryNumber;
	}
}