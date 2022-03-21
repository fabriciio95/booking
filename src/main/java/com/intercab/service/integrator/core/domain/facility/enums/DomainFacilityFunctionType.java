package com.intercab.service.integrator.core.domain.facility.enums;

public enum DomainFacilityFunctionType {
	TRIANGULATION("TRIANGULATION", "Triangulation"),
	OCEAN_TERMINAL("OCEAN_TERMINAL", "Ocean Terminal"),
	BARGE_TERMINAL("BARGE_TERMINAL", "Bage Terminal"),
	FEEDER_TERMINAL("FEEDER_TERMINAL", "Feeder Terminal"),
	RAIL_TERMINAL("RAIL_TERMINAL", "Rail Terminal"),
	EMPTY_DEPOT("EMPTY_DEPOT", "Empty Depot"),
	FULL_DEPOT("FULL_DEPOT", "Full Depot"),
	HAULIER_DEPOT("HAULIER_DEPOT", "Haulier Depot"),
	OFFHIRE_DEPOT("OFFHIRE_DEPOT", "Offhire Depot"),
	ONHIRE_DEPOT("ONHIRE_DEPOT", "Onhire Depot"),
	REPAIR_FACILITY("REPAIR_FACILITY", "Repair Facility"),
	WAREHOUSE("WAREHOUSE", "Warehouse"),
	SHIPPER_POOL("SHIPPER_POOL", "Shipper Pool"),
	CUSTOM_S_BONDED_DEPOT("CUSTOM_S_BONDED_DEPOT", "Custom S Boned Depot"),
	CUSTOM_S_OFFICE("CUSTOM_S_OFFICE", "Custom S Office"),
	CUSTOMER_S_PREMISES("CUSTOMER_S_PREMISES", "Customer S Premises"),
	SCAN_FACILITY("SCAN_FACILITY", "Scan Facility"),
	FUMIGATION_FACILITY("FUMIGATION_FACILITY", "Fumigation Station"),
	VETERINARY_STATION("VETERINARY_STATION", "Veterinary Station"),
	PACKING_STATION("PACKING_STATION", "Packing Station");
	
	private String code;
	private String description;

	private DomainFacilityFunctionType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}
}