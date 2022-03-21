package com.intercab.service.integrator.core.service.city;

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
import com.intercab.service.integrator.core.application.mappers.city.CityMapper;
import com.intercab.service.integrator.core.domain.city.modal.CityEventHub;
import com.intercab.service.integrator.core.domain.city.modal.CityRequest;
import com.intercab.service.integrator.core.domain.city.modal.CityRequestService;
import com.intercab.service.integrator.core.domain.city.validator.CityValidator;
import com.intercab.service.integrator.core.domain.log.modal.CityLog;
import com.intercab.service.integrator.core.service.log.ILogService;
import com.intercab.service.integrator.core.service.token.ITokenService;

@Service
public class CityService implements ICityService {
	private final Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired
	private ILogService logService;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private Environment env;

	@Override
	public void processData(String message) {

		CityLog citLog = new CityLog(new Date(), message);

		try {
			Gson gson = new Gson();
			CityEventHub city = gson.fromJson(message, CityEventHub.class);
			citLog.setAction(city.getProcessData().getChangeType());

			//this.sendToService(city, citLog);
			this.sendToServiceGeneralRegister(city, citLog);

			this.logger.info("Globe-City - Integrated Message");
		} catch (JsonSyntaxException error) {
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getCode(), error.getMessage()));

			citLog.setError(errorList);
			citLog.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), DomainStatus.UNPROCESSED.getDescription(), error.getStackTrace(), citLog);

			this.logger.info("Globe-City - Exception found and sent to CosmosDB - {}", citLog.getError());
		}
	}

	// TODO: Método desistido. Refatorar!
	private void sendToService(CityEventHub CityEventHub, CityLog log) {
		try {
			CityValidator.request(CityEventHub, log);

			CityRequestService request = CityMapper.createRequestFromServiceBus(CityEventHub);

			URL url = new URL(KeyVaultUtils.getSecretValue(this.env, "intercab.service.city.integrator.url"));
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

			CityValidator.response(con, log);
		} catch (ApplicationBusinessException error) {
			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		} catch (Exception error) {
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), error.getMessage()));

			log.setError(errorList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		}
	}

	private void sendToServiceGeneralRegister(CityEventHub CityEventHub, CityLog log) {
		try {
			CityValidator.request(CityEventHub, log);

			CityRequest request = CityMapper.createRequestGRFromServiceBus(CityEventHub);

			URL url = new URL(KeyVaultUtils.getSecretValue(this.env, "intercab.service.city.generalregister.integrator.url"));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("locale", "en");
			con.setRequestProperty("Consumer-Key", this.env.getProperty("consumerkey"));
			con.setRequestProperty("Authorization", tokenService.getToken());
			con.setDoOutput(true);


			String json = new Gson().toJson(request);

			ObjectMapper mapper = new ObjectMapper();
			String jsonInputString = mapper.writeValueAsString(request);


			try (OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			CityValidator.response(con, log);
		} catch (ApplicationBusinessException error) {
			error.printStackTrace();
			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		} catch (Exception error) {
			error.printStackTrace();
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getDescription(), error.getMessage()));

			log.setError(errorList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), error.getLocalizedMessage(), error.getStackTrace(), log);
		}
	}
}