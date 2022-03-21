package com.intercab.service.integrator.core.domain.vendor.modal;

import java.io.Serializable;

public class PartnerRoles implements Serializable {
	private static final long serialVersionUID = 8421348226794632865L;

	private long version;
	private String partnerRole;
	private PartnerFunctions[] partnerFunctions;
	private BillToParties[] billToParties;

	public PartnerRoles() {
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getPartnerRole() {
		return partnerRole;
	}

	public void setPartnerRole(String partnerRole) {
		this.partnerRole = partnerRole;
	}

	public PartnerFunctions[] getPartnerFunctions() {
		return partnerFunctions;
	}

	public void setPartnerFunctions(PartnerFunctions[] partnerFunctions) {
		this.partnerFunctions = partnerFunctions;
	}

	public BillToParties[] getBillToParties() {
		return billToParties;
	}

	public void setBillToParties(BillToParties[] billToParties) {
		this.billToParties = billToParties;
	}
}