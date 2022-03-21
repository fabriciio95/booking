package com.intercab.service.integrator.config;

import com.intercab.keyvault.KeyVaultUtils;
import com.intercab.service.integrator.core.commons.DomainEnvVariable;
import com.intercab.utils.EnvironmentVariableUtils;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.intercab.service.integrator.core.infrastructure.repository"})
public class MongoDBConfig extends AbstractMongoClientConfiguration {
	@Autowired
	private Environment env;

	@Bean
	public MongoClientOptions mongoClientOptions() {
		return MongoClientOptions.builder()
			.connectionsPerHost(Integer.valueOf(this.env.getProperty("mongodb.connections.perhost")))
			.threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(this.env.getProperty("mongodb.threads.connection.multiplier")))
			.connectTimeout(Integer.valueOf(this.env.getProperty("mongodb.connect.timeout")))
			.maxWaitTime(Integer.valueOf(this.env.getProperty("mongodb.max.wait.time")))
			.retryReads(Boolean.valueOf(this.env.getProperty("mongodb.retry.reads")))
			.socketTimeout(Integer.valueOf(this.env.getProperty("mongodb.socket.timeout")))
			.heartbeatSocketTimeout(Integer.valueOf(this.env.getProperty("mongodb.heartbeat.socket.timeout")))
			.minHeartbeatFrequency(Integer.valueOf(this.env.getProperty("mongodb.minheartbeat.frequency")))
			.build();
	}

	@Override
	public MongoClient mongoClient() {
		String secretKeyVault = EnvironmentVariableUtils.getEnv(DomainEnvVariable.INTERCAB_ERRORLOG_DATABASE_KEYVAULT_SECRET.getDescription(), DomainEnvVariable.INTERCAB_ERRORLOG_DATABASE_KEYVAULT_SECRET.getDefaultValue());
		String secretValue = KeyVaultUtils.getSecretValue(this.env, secretKeyVault);

		return MongoClients.create(secretValue);
	}

	@Override
	protected String getDatabaseName() {
		return KeyVaultUtils.getSecretValue(this.env, "mongodb.database");
	}
}