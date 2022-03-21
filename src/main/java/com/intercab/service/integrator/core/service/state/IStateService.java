package com.intercab.service.integrator.core.service.state;

import org.springframework.stereotype.Component;

@Component
public interface IStateService {
	void processData(String message);
}