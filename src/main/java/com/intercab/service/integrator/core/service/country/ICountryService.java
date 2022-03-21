package com.intercab.service.integrator.core.service.country;

import org.springframework.stereotype.Component;

@Component
public interface ICountryService {
	void processData(String message);
}