package com.intercab.service.integrator.core.commons;

public enum DomainEnvVariable {
	INTERCAB_EVENTHUB_GLOBE_KEYVAULT_SECRET("INTERCAB_EVENTHUB_GLOBE_KEYVAULT_SECRET", "ehauth.globe.business.partner.name.globe.IntercabIntegrationService.listen.connstring.primary"),
	INTERCAB_ERRORLOG_DATABASE_KEYVAULT_SECRET("INTERCAB_ERRORLOG_DATABASE_KEYVAULT_SECRET", "intercab.log.cosmosdb.primary.connectionstring"),
	INTERCAB_AZURE_STORAGE_KEYVAULT_SECRET("INTERCAB_AZURE_STORAGE_KEYVAULT_SECRET","storage.primary.connectionstring"),
	INTERCAB_EVENTHUB_GLOBE_PROPERTIES_FILTER("INTERCAB_EVENTHUB_GLOBE_PROPERTIES_FILTER","eventName");

	private String description;

	private String defaultValue;

	private DomainEnvVariable(String description, String defaultValue) {
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public String getDescription() {
		return this.description;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
}