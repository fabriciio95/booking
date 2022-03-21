package com.intercab.service.integrator.core.controller;

import javax.servlet.http.HttpServletResponse;

import com.intercab.service.integrator.core.service.city.ICityService;
import com.intercab.service.integrator.core.service.country.ICountryService;
import com.intercab.service.integrator.core.service.facility.IFacilityService;
import com.intercab.service.integrator.core.service.location.ILocationService;
import com.intercab.service.integrator.core.service.state.IStateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "v1/integrator")
@CrossOrigin(origins = "${api.access.control.allow.origin}")
@Tag(name = "Interation - Location", description = "Interation API")
public class LocationController {
	@Autowired
	private Environment env;

	@Autowired
	private IFacilityService facilityService;
	@Autowired
	private ICountryService countryService;
	@Autowired
	private IStateService stateService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private ILocationService locationService;

	@Operation(
		summary = "Globe - Facility",
		description = "Globe - Facility"
	)
	@PostMapping(
		value = "/facility",
		consumes = "application/json",
		produces = "application/json"
	)
	public String processFacility(@RequestBody String strJson, HttpServletResponse serveletResponse) {
		if (Boolean.TRUE.equals(this.env.containsProperty("server.send.test")) && Boolean.TRUE.equals(Boolean.valueOf(this.env.getProperty("server.send.test")))) {
			facilityService.processData(strJson);
			return "End of Facility processing test";
		} else {
			return "This function is not configured";
		}
	}

	@Operation(
		summary = "Globe - Country",
		description = "Globe - Country"
	)
	@PostMapping(
		value = "/country",
		consumes = "application/json",
		produces = "application/json"
	)
	public String processCountry(@RequestBody String strJson, HttpServletResponse serveletResponse) {
		if (Boolean.TRUE.equals(this.env.containsProperty("server.send.test")) && Boolean.TRUE.equals(Boolean.valueOf(this.env.getProperty("server.send.test")))) {
			countryService.processData(strJson);
			return "End of Facility processing test";
		} else {
			return "This function is not configured";
		}
	}

	@Operation(
		summary = "Globe - State",
		description = "Globe - State"
	)
	@PostMapping(
		value = "/state",
		consumes = "application/json",
		produces = "application/json"
	)
	public String processState(@RequestBody String strJson, HttpServletResponse serveletResponse) {
		if (Boolean.TRUE.equals(this.env.containsProperty("server.send.test")) && Boolean.TRUE.equals(Boolean.valueOf(this.env.getProperty("server.send.test")))) {
			stateService.processData(strJson);
			return "End of State processing test";
		} else {
			return "This function is not configured";
		}
	}

	@Operation(
		summary = "Globe - City",
		description = "Globe - City"
	)
	@PostMapping(
		value = "/city",
		consumes = "application/json",
		produces = "application/json"
	)
	public String processCity(@RequestBody String strJson, HttpServletResponse serveletResponse) {
		if (Boolean.TRUE.equals(this.env.containsProperty("server.send.test")) && Boolean.TRUE.equals(Boolean.valueOf(this.env.getProperty("server.send.test")))) {
			cityService.processData(strJson);
			return "End of City processing test";
		} else {
			return "This function is not configured";
		}
	}

	@Operation(
		summary = "Globe - Location",
		description = "Globe - Location"
	)
	@PostMapping(
		value = "/location",
		consumes = "application/json",
		produces = "application/json"
	)
	public String processLocation(@RequestBody String strJson, HttpServletResponse serveletResponse) {
		if (Boolean.TRUE.equals(this.env.containsProperty("server.send.test")) && Boolean.TRUE.equals(Boolean.valueOf(this.env.getProperty("server.send.test")))) {
			locationService.processData(strJson);
			return "End of Location processing test";
		} else {
			return "This function is not configured";
		}
	}
}