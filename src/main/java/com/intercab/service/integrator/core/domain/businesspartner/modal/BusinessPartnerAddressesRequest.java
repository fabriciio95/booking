package com.intercab.service.integrator.core.domain.businesspartner.modal;

import java.io.Serializable;

public class BusinessPartnerAddressesRequest implements Serializable {
	private static final long serialVersionUID = -7349149959835478595L;

	private Long busuCityId;
	private String busuCityName;
	private Long busuStateId;
	private String address;
	private String number;
	private String zipCode;
	private String district;
	private String complement;
	private boolean mainFacility;
	private String globeExpiryDate;
	private String globeSfc;
	private Long globeBPnumber;
	private String globeStatusDescription;
	private String source;

	public Long getBusuCityId() {
		return busuCityId;
	}

	public void setBusuCityId(Long busuCityId) {
		this.busuCityId = busuCityId;
	}

	public String getBusuCityName() {
		return busuCityName;
	}

	public void setBusuCityName(String busuCityName) {
		this.busuCityName = busuCityName;
	}

	public Long getBusuStateId() {
		return busuStateId;
	}

	public void setBusuStateId(Long busuStateId) {
		this.busuStateId = busuStateId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public boolean isMainFacility() {
		return mainFacility;
	}

	public void setMainFacility(boolean mainFacility) {
		this.mainFacility = mainFacility;
	}

	public String getGlobeExpiryDate() {
		return globeExpiryDate;
	}

	public void setGlobeExpiryDate(String globeExpiryDate) {
		this.globeExpiryDate = globeExpiryDate;
	}

	public String getGlobeSfc() {
		return globeSfc;
	}

	public void setGlobeSfc(String globeSfc) {
		this.globeSfc = globeSfc;
	}

	public Long getGlobeBPnumber() {
		return globeBPnumber;
	}

	public void setGlobeBPnumber(Long globeBPnumber) {
		this.globeBPnumber = globeBPnumber;
	}

	public String getGlobeStatusDescription() {
		return globeStatusDescription;
	}

	public void setGlobeStatusDescription(String globeStatusDescription) {
		this.globeStatusDescription = globeStatusDescription;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}