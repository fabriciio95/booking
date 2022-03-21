package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerAddressesOverviews implements Serializable {
	private static final long serialVersionUID = 3114984609897507034L;

	private String addressType;
	private String cityNumber;
	private String countryNumber;
	private String effectiveDate;
	private String expiryDate;
	private String mlCityName;
	private String mlCountryName;
	private String mlPostalCode;
	private String mlStreetName1;
	private String mlStreetNumber;
	private String postalCode;
	private String stateNumber;
	private String status;
	private String streetName1;
	private String streetName2;
	private String streetName3;
	private String streetNumber;
	private String district;
	private String buildingName;
	private String floor;
	private String roomOrSuiteNumber;
	private Long version;

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getCityNumber() {
		return cityNumber;
	}

	public void setCityNumber(String cityNumber) {
		this.cityNumber = cityNumber;
	}

	public String getCountryNumber() {
		return countryNumber;
	}

	public void setCountryNumber(String countryNumber) {
		this.countryNumber = countryNumber;
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

	public String getMlCityName() {
		return mlCityName;
	}

	public void setMlCityName(String mlCityName) {
		this.mlCityName = mlCityName;
	}

	public String getMlCountryName() {
		return mlCountryName;
	}

	public void setMlCountryName(String mlCountryName) {
		this.mlCountryName = mlCountryName;
	}

	public String getMlPostalCode() {
		return mlPostalCode;
	}

	public void setMlPostalCode(String mlPostalCode) {
		this.mlPostalCode = mlPostalCode;
	}

	public String getMlStreetName1() {
		return mlStreetName1;
	}

	public void setMlStreetName1(String mlStreetName1) {
		this.mlStreetName1 = mlStreetName1;
	}

	public String getStreetName2() {
		return streetName2;
	}

	public String getMlStreetNumber() {
		return mlStreetNumber;
	}

	public void setMlStreetNumber(String mlStreetNumber) {
		this.mlStreetNumber = mlStreetNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStateNumber() {
		return stateNumber;
	}

	public void setStateNumber(String stateNumber) {
		this.stateNumber = stateNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStreetName1() {
		return streetName1;
	}

	public void setStreetName1(String streetName1) {
		this.streetName1 = streetName1;
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

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
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

	public String getRoomOrSuiteNumber() {
		return roomOrSuiteNumber;
	}

	public void setRoomOrSuiteNumber(String roomOrSuiteNumber) {
		this.roomOrSuiteNumber = roomOrSuiteNumber;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}