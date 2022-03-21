package com.intercab.service.integrator.core.service.city;

import org.springframework.stereotype.Component;

@Component
public interface ICityService {
	void processData(String message);
}