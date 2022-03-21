package com.intercab.service.integrator.core.domain.log.modal;

import com.intercab.log.core.model.Log;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "#{@configRepositoryCustomImpl.getCollectionName()}")
public class IntercabLog extends Log {
	private static final long serialVersionUID = -1555058253632310314L;

	public IntercabLog(Log log) {
		this.setId(log.getId());
		this.setCreatedDate(log.getCreatedDate());
		this.setInterfaceName(log.getInterfaceName());
		this.setInterfaceId(log.getInterfaceId());
		this.setEnvironment(log.getEnvironment());
		this.setErrorDetails(log.getErrorDetails());
	}
}