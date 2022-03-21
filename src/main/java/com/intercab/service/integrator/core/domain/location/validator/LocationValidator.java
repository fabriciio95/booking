package com.intercab.service.integrator.core.domain.location.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.apache.commons.lang3.StringUtils;

import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.domain.log.modal.LocationLog;

public class LocationValidator {
	public static void response(HttpURLConnection con, LocationLog log) throws IOException, ApplicationBusinessException {
		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			StringBuilder response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			if (StringUtils.contains(response.toString(), "error")) {
				LogMoreDetails error = new LogMoreDetails(String.valueOf(responseCode), response.toString());
				log.setStatus(DomainStatus.UNPROCESSED.getDescription());
				log.setError(error);

				throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
			}
		} else if (responseCode != HttpURLConnection.HTTP_OK) {
			LogMoreDetails error = new LogMoreDetails(String.valueOf(responseCode), con.getResponseMessage());
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());
			log.setError(error);

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}
	}
}
