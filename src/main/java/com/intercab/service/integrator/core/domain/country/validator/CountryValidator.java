package com.intercab.service.integrator.core.domain.country.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainReturnCode;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.domain.country.modal.CountryEventHub;
import com.intercab.service.integrator.core.domain.country.modal.CountryNaming;
import com.intercab.service.integrator.core.domain.log.modal.CountryLog;

import org.apache.commons.lang3.StringUtils;

public class CountryValidator {
	public static void request(CountryEventHub countryEventHub, CountryLog log) throws ApplicationBusinessException {
		List<LogMoreDetails> errorLogList = new ArrayList<LogMoreDetails>();

		if (countryEventHub.getBusinessObject().getCode() == null) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Code")));
		}

		requestDataNaming(countryEventHub.getBusinessObject().getNaming(), errorLogList);

		if (!errorLogList.isEmpty()) {
			log.setError(errorLogList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}
	}

	private static void requestDataNaming(CountryNaming[] naming, List<LogMoreDetails> errorLogList) {
		Boolean informedNaming = Boolean.FALSE;

		for (int i = 0; i < naming.length; i++) {
			if (naming[i].getName() != null) {
				informedNaming = Boolean.TRUE;
				break;
			}
		}

		if (Boolean.FALSE.equals(informedNaming)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Name")));
		}
	}

	public static void requestForGeneralRegister(CountryEventHub countryEventHub, CountryLog log) throws ApplicationBusinessException {
		List<LogMoreDetails> errorLogList = new ArrayList<LogMoreDetails>();

		if (countryEventHub.getBusinessObject().getCode() == null) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Code")));
		}

		requestDataNamingForGeneralRegister(countryEventHub.getBusinessObject().getNaming(), errorLogList);

		if (!errorLogList.isEmpty()) {
			log.setError(errorLogList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}
	}

	private static void requestDataNamingForGeneralRegister(CountryNaming[] naming, List<LogMoreDetails> errorLogList) {
		Boolean informedCode = Boolean.TRUE;
		Boolean informedNaming = Boolean.TRUE;
		Boolean informedEffectiveFrom = Boolean.TRUE;
		Boolean informedEffectiveTo = Boolean.TRUE;

		for (int i = 0; i < naming.length; i++) {
			if (StringUtils.isBlank(naming[i].getCode())) {
				informedCode = Boolean.FALSE;
			}

			if (StringUtils.isBlank(naming[i].getName())) {
				informedNaming = Boolean.FALSE;
			}

			if (StringUtils.isBlank(naming[i].getEffectiveFrom())) {
				informedEffectiveFrom = Boolean.FALSE;
			}

			if (StringUtils.isBlank(naming[i].getEffectiveTo())) {
				informedEffectiveTo = Boolean.FALSE;
			}
		}

		if (Boolean.FALSE.equals(informedCode)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Naming Code")));
		}

		if (Boolean.FALSE.equals(informedNaming)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Name")));
		}

		if (Boolean.FALSE.equals(informedEffectiveFrom)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Naming Effective From")));
		}

		if (Boolean.FALSE.equals(informedEffectiveTo)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Naming Effective To")));
		}
	}

	public static void response(HttpURLConnection con, CountryLog log) throws IOException, ApplicationBusinessException {
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