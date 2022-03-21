package com.intercab.service.integrator.core.infrastructure.repository.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.intercab.keyvault.KeyVaultUtils;

@Component
public class ConfigRepositoryCustomImpl implements ConfigRepositoryCustom {
	@Autowired
	private Environment env;

	@Override
	public String getCollectionName() {
		return KeyVaultUtils.getSecretValue(env, "mongodb.integrator.collection.log");
	}
}