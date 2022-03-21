package com.intercab.service.integrator.core.service.businesspartner;

import org.springframework.stereotype.Component;

@Component
public interface IBusinessPartnerService {
	void processData(String message);
}