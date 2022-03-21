package com.intercab.service.integrator.core.service.location;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.keyvault.KeyVaultUtils;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.application.mappers.location.LocationMapper;
import com.intercab.service.integrator.core.domain.location.model.LocationEventHub;
import com.intercab.service.integrator.core.domain.location.model.LocationRequest;
import com.intercab.service.integrator.core.domain.location.validator.LocationValidator;
import com.intercab.service.integrator.core.domain.log.modal.LocationLog;
import com.intercab.service.integrator.core.service.log.ILogService;
import com.intercab.service.integrator.core.service.token.ITokenService;

@Service
public class LocationService implements ILocationService {
	private static final String ERROR_ID = "error.id";

	private final Logger logger = LoggerFactory.getLogger(LocationService.class);

	@Autowired
	private ILogService logService;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private Environment env;

	@Override
	public void processData(String message) {
		logger.info("Globe-Location - Process Message");

		LocationLog log = new LocationLog(new Date(), message);
		try {
			Gson gson = new Gson();
			LocationEventHub location = gson.fromJson(message, LocationEventHub.class);

			log.setAction(location.getProcessData().getChangeType());
			this.sendToService(location, log);

			logger.info("Globe-Location - Integrated Message");
		} catch (JsonSyntaxException error) {
			List<LogMoreDetails> errorList = new ArrayList<>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getCode(), error.getMessage()));

			log.setError(errorList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty(ERROR_ID), DomainStatus.UNPROCESSED.getDescription(), DomainStatus.UNPROCESSED.getDescription(), error.getStackTrace(), log);

			logger.info("Globe-Location - Exception found and sent to CosmosDB - {}", log.getError());
		}
	}

	@Override
	public void sendToService(LocationEventHub entityEventHub, LocationLog log) {
		try {
			LocationRequest request = LocationMapper.createRequestFromLocation(entityEventHub);
			URL url = new URL(KeyVaultUtils.getSecretValue(env, "intercab.service.location.integrator.url"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("locale", "en");
			con.setRequestProperty("Consumer-Key", this.env.getProperty("consumerkey"));
			con.setRequestProperty("Authorization", tokenService.getToken());

			con.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
			String jsonInputString = mapper.writeValueAsString(request);

			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			LocationValidator.response(con, log);
		} catch (ApplicationBusinessException error) {
			this.logService.save(this.env.getProperty(ERROR_ID), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		} catch (Exception error) {
			List<LogMoreDetails> errorList = new ArrayList<>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), error.getMessage()));

			log.setError(errorList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty(ERROR_ID), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		}
	}
}