package com.intercab.service.integrator.core.domain.city.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainReturnCode;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.domain.city.modal.CityEventHub;
import com.intercab.service.integrator.core.domain.city.modal.CityNaming;
import com.intercab.service.integrator.core.domain.log.modal.CityLog;

public class CityValidator {
	public static void request(CityEventHub cityEventHub, CityLog log) throws ApplicationBusinessException {
		List<LogMoreDetails> errorLogList = new ArrayList<LogMoreDetails>();

		if (cityEventHub.getBusinessObject().getCode() == null) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Code")));
		}

		if (cityEventHub.getBusinessObject().getState() == null) {
			// errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "State")));
		}

		requestDataNaming(cityEventHub.getBusinessObject().getNaming(), errorLogList);

		if (!errorLogList.isEmpty()) {
			log.setError(errorLogList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}
	}

	private static void requestDataNaming(List<CityNaming> naming, List<LogMoreDetails> errorLogList) {
		Boolean informedNaming = Boolean.FALSE;

		for (CityNaming cityName : naming) {
			if(cityName.getName() != null) {
				informedNaming = Boolean.TRUE;
				break;
			}
		}

		if (Boolean.FALSE.equals(informedNaming)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Name")));
		}
	}

	public static void response(HttpURLConnection con, CityLog log) throws IOException, ApplicationBusinessException {
		int responseCode = con.getResponseCode();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			StringBuffer response = new StringBuffer();

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