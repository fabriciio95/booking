package com.intercab.service.integrator.core.domain.state.validator;

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
import com.intercab.service.integrator.core.domain.log.modal.StateLog;
import com.intercab.service.integrator.core.domain.state.modal.StateEventHub;
import com.intercab.service.integrator.core.domain.state.modal.StateNaming;

import org.apache.commons.lang3.StringUtils;

public class StateValidator {
	public static void request(StateEventHub stateEventHub, StateLog log) throws ApplicationBusinessException {
		List<LogMoreDetails> errorLogList = new ArrayList<LogMoreDetails>();

		if (stateEventHub.getBusinessObject().getCode() == null) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(),
					MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Code")));
		}

		if (stateEventHub.getBusinessObject().getCountry() == null) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(),
					MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Country")));
		}

		requestDataNaming(stateEventHub.getBusinessObject().getNaming(), errorLogList);

		if (!errorLogList.isEmpty()) {
			log.setError(errorLogList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}
	}

	public static void requestForGeneralRegister(StateEventHub stateEventHub, StateLog log) throws ApplicationBusinessException {
		List<LogMoreDetails> errorLogList = new ArrayList<LogMoreDetails>();

		if (stateEventHub.getBusinessObject().getCode() == null) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(),
					MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Code")));
		}

		if (stateEventHub.getBusinessObject().getCountry() == null) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(),
					MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Country")));
		}

		requestDataNamingForGeneralRegister(stateEventHub.getBusinessObject().getNaming(), errorLogList);

		if (!errorLogList.isEmpty()) {
			log.setError(errorLogList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}
	}

	private static void requestDataNamingForGeneralRegister(StateNaming[] naming, List<LogMoreDetails> errorLogList) {
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

	private static void requestDataNaming(StateNaming[] naming, List<LogMoreDetails> errorLogList) {
		Boolean informedCode = Boolean.FALSE;
		Boolean informedNaming = Boolean.FALSE;

		for (int i = 0; i < naming.length; i++) {
			if (naming[i].getCode() != null) {
				informedCode = Boolean.TRUE;
				break;
			}

			if (naming[i].getName() != null) {
				informedNaming = Boolean.TRUE;
				break;
			}
		}

		if (Boolean.FALSE.equals(informedCode)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Naming Code")));
		}

		if (Boolean.FALSE.equals(informedNaming)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Name")));
		}
	}

	public static void response(HttpURLConnection con, StateLog log) throws IOException, ApplicationBusinessException {
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