package com.intercab.service.integrator.core.application.mappers.country;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.mappers.DataMapper;
import com.intercab.service.integrator.core.domain.country.modal.CountryEventHub;
import com.intercab.service.integrator.core.domain.country.modal.CountryNaming;
import com.intercab.service.integrator.core.domain.country.modal.CountryNamingRequest;
import com.intercab.service.integrator.core.domain.country.modal.CountryRequest;
import com.intercab.service.integrator.core.domain.country.modal.CountryRequestForGeneralRegister;
import com.intercab.service.integrator.core.domain.log.modal.CountryLog;

import org.apache.commons.lang3.StringUtils;

public class CountryMapper {
	public static CountryRequest createRequestFromServiceBus(CountryEventHub countryEventHub) {
		CountryRequest request = new CountryRequest();
		request.setBusuCountryId(Long.valueOf(countryEventHub.getBusinessObject().getCode()));
		request.setName(getCurrentVersionName(countryEventHub.getBusinessObject().getNaming()));
		request.setProcessData(DataMapper.createProcessDataFromRequest(countryEventHub.getProcessData()));

		return request;
	}

	public static String getCurrentVersionName(CountryNaming[] namingArray) {
		String name = StringUtils.EMPTY;
		Long version = 0L;

		for (int i = 0; i < namingArray.length; i++) {
			if (namingArray[i] != null && namingArray[i].getVersion() > version) {
				version = namingArray[i].getVersion();
				name = namingArray[i].getName();
			}
		}

		return name;
	}

	public static CountryRequestForGeneralRegister createRequestFromServiceBusForGeneralRegister(CountryEventHub countryEventHub) {
		CountryRequestForGeneralRegister request = new CountryRequestForGeneralRegister();

		request.setBusuCountryId(Long.valueOf(countryEventHub.getBusinessObject().getCode()));
		getNameList(request, countryEventHub.getBusinessObject().getNaming());
		request.setProcessData(DataMapper.createProcessDataFromRequest(countryEventHub.getProcessData()));

		return request;
	}

	public static void getNameList(CountryRequestForGeneralRegister request, CountryNaming[] namingArray) {
		List<CountryNamingRequest> countryNamingRequestList = new ArrayList<CountryNamingRequest>();

		for (int i = 0; i < namingArray.length; i++) {
			countryNamingRequestList.add(createCountryNamingRequestFromCountryNaming(namingArray[i]));
		}

		request.setNaming(countryNamingRequestList);
	}

	public static CountryNamingRequest createCountryNamingRequestFromCountryNaming(CountryNaming countryNaming) {
		CountryNamingRequest countryNamingRequest = new CountryNamingRequest();

		countryNamingRequest.setCode(countryNaming.getCode());
		countryNamingRequest.setName(countryNaming.getName());
		countryNamingRequest.setEffectiveFrom(countryNaming.getEffectiveFrom());
		countryNamingRequest.setEffectiveTo(countryNaming.getEffectiveTo());

		return countryNamingRequest;
	}

	@SuppressWarnings("unchecked")
	public static List<LogMoreDetails> createMoreDetails(CountryLog countryLog) {
		Gson gson = new Gson();

		List<LogMoreDetails> list = new ArrayList<LogMoreDetails>();
		list.addAll((Collection<? extends LogMoreDetails>) countryLog.getError());
		list.add(new LogMoreDetails("DATA", gson.toJson(countryLog.getData())));

		return list;
	}
}