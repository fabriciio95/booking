package com.intercab.service.integrator.core.controller;

import javax.servlet.http.HttpServletResponse;

import com.intercab.service.integrator.core.service.businesspartner.IBusinessPartnerService;

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
@Tag(name = "Interation - Business Partner", description = "Interation API")
public class BusinessPartnerController {
	@Autowired
	private Environment env;

	@Autowired
	private IBusinessPartnerService businessPartnerService;

	@Operation(
		summary = "Globe - Business Partner",
		description = "Globe - Business Partner"
	)
	@PostMapping(
		value = "/business-partner",
		consumes = "application/json",
		produces = "application/json"
	)
	public String processBusinessPartner(@RequestBody String strJson, HttpServletResponse serveletResponse) {
		if (Boolean.TRUE.equals(this.env.containsProperty("server.send.test")) && Boolean.TRUE.equals(Boolean.valueOf(this.env.getProperty("server.send.test")))) {
			businessPartnerService.processData(strJson);
			return "End of Business Partner processing test";
		} else {
			return "This function is not configured";
		}
	}
}