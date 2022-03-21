package com.intercab.service.integrator.core.service.token;

import org.springframework.stereotype.Component;

import com.intercab.exception.core.ApplicationBusinessException;

@Component
public interface ITokenService {
	String getToken() throws ApplicationBusinessException;
}