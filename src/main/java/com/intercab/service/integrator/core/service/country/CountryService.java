package com.intercab.service.integrator.core.service.country;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.keyvault.KeyVaultUtils;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.application.mappers.country.CountryMapper;
import com.intercab.service.integrator.core.domain.country.modal.CountryEventHub;
import com.intercab.service.integrator.core.domain.country.modal.CountryRequest;
import com.intercab.service.integrator.core.domain.country.modal.CountryRequestForGeneralRegister;
import com.intercab.service.integrator.core.domain.country.validator.CountryValidator;
import com.intercab.service.integrator.core.domain.log.modal.CountryLog;
import com.intercab.service.integrator.core.service.log.ILogService;
import com.intercab.service.integrator.core.service.token.ITokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class CountryService implements ICountryService {
	private final Logger logger = LoggerFactory.getLogger(CountryService.class);

	@Autowired
	private ILogService logService;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private Environment env;

	@Override
	public void processData(String message) {
		this.logger.info("Globe-Country - Process Message");

		CountryLog countryLog = new CountryLog(new Date(), message);

		try {
			Gson gson = new Gson();
			CountryEventHub country = gson.fromJson(message, CountryEventHub.class);
			countryLog.setAction(country.getProcessData().getChangeType());

			this.sendToService(country, countryLog);
			this.sendToServiceGeneralRegister(country, countryLog);

			this.logger.info("Globe-Country - Integrated Message");
		} catch (JsonSyntaxException error) {
			List<LogMoreDetails> errorList = new ArrayList<LogMoreDetails>();
			errorList.add(new LogMoreDetails(DomainStatus.UNPROCESSED.getCode(), error.getMessage()));

			countryLog.setError(errorList);
			countryLog.setStatus(DomainStatus.UNPROCESSED.getDescription());

			this.logService.save(this.env.getProperty("error.id"), DomainStatus.UNPROCESSED.getDescription(), DomainStatus.UNPROCESSED.getDescription(), error.getStackTrace(), countryLog);

			this.logger.info("Globe-Country - Exception found and sent to CosmosDB - {}", countryLog.getError());
		}
	}

	private void sendToService(CountryEventHub countryEventHub, CountryLog log) {
		try {
			CountryValidator.request(countryEventHub, log);

			CountryRequest request = CountryMapper.createRequestFromServiceBus(countryEventHub);

			URL url = new URL(KeyVaultUtils.getSecretValue(this.env, "intercab.service.country.integrator.url"));
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

			CountryValidator.response(con, log);
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

	private void sendToServiceGeneralRegister(CountryEventHub countryEventHub, CountryLog log) {
		try {
			CountryValidator.requestForGeneralRegister(countryEventHub, log);

			CountryRequestForGeneralRegister request = CountryMapper.createRequestFromServiceBusForGeneralRegister(countryEventHub);

			URL url = new URL(KeyVaultUtils.getSecretValue(this.env, "intercab.service.country.generalregister.integrator.url"));
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

			CountryValidator.response(con, log);
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
}