package com.intercab.service.integrator.core.application.mappers.location;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.service.integrator.core.application.domains.DomainReturnCode;
import com.intercab.service.integrator.core.domain.location.model.Location;
import com.intercab.service.integrator.core.domain.location.model.LocationCsCode;
import com.intercab.service.integrator.core.domain.location.model.LocationCsCodeRequest;
import com.intercab.service.integrator.core.domain.location.model.LocationEventHub;
import com.intercab.service.integrator.core.domain.location.model.LocationNaming;
import com.intercab.service.integrator.core.domain.location.model.LocationNamingRequest;
import com.intercab.service.integrator.core.domain.location.model.LocationRequest;
import com.intercab.service.integrator.core.domain.location.model.LogHierarchy;
import com.intercab.service.integrator.core.domain.location.model.LogHierarchyRequest;
import com.intercab.utils.DateUtils;

public class LocationMapper {

	public static LocationRequest createRequestFromLocation(LocationEventHub locationEventHub) throws ApplicationBusinessException {
		Location location = locationEventHub.getBusinessObject();
		LocationRequest request = new LocationRequest();

		request.setLocationNumber(location.getCode());
		request.setCountryCode(location.getCountry());
		request.setStateCode(location.getState());
		request.setPortFlag(location.getIsPort());

		try {
			Date validFrom = DateUtils.stringToDate(location.getValidFrom(), DateUtils.YYYY_MM_DD);
			request.setValidFrom(validFrom);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Valid From"));
		}

		try {
			Date validTo = DateUtils.stringToDate(location.getValidTo(), DateUtils.YYYY_MM_DD);
			request.setValidTo(validTo);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Valid To"));
		}

		if (location.getLocationDetails() != null) {
			request.setTimeZone(location.getLocationDetails().getTimezone());
		}

		List<LocationNamingRequest> locationNamingListToLocationNamingRequestList = locationNamingListToLocationNamingRequestList(location.getNaming());
		request.setNaming(locationNamingListToLocationNamingRequestList);

		List<LogHierarchyRequest> logHierarchyRequestList = locationLogHierarchyListToLocationLogHierarchyRequestList(location.getLogHierarchy());
		request.setLogHierarchy(logHierarchyRequestList);

		List<LocationCsCodeRequest> locationCsCodeRequestList = locationCsCodeListToLocationCsCodeRequestList(location.getLocationCsCode());
		request.setLocationCsCode(locationCsCodeRequestList);

		return request;
	}

	public static List<LocationNamingRequest> locationNamingListToLocationNamingRequestList(List<LocationNaming> locationNamingList) throws ApplicationBusinessException {
		List<LocationNamingRequest> locationNamingRequestList = new ArrayList<>();

		for (LocationNaming locationNaming : locationNamingList) {
			locationNamingRequestList.add(locationNamingToLocationNamingRequest(locationNaming));
		}

		return locationNamingRequestList;
	}

	public static LocationNamingRequest locationNamingToLocationNamingRequest(LocationNaming locationNaming) throws ApplicationBusinessException {
		LocationNamingRequest locationNamingRequest = new LocationNamingRequest();

		locationNamingRequest.setCode(locationNaming.getCode());
		locationNamingRequest.setName(locationNaming.getName());
		locationNamingRequest.setVersion(locationNaming.getVersion());

		try {
			Date effectiveFrom = DateUtils.stringToDate(locationNaming.getEffectiveFrom(), DateUtils.YYYY_MM_DD);
			locationNamingRequest.setEffectiveFrom(effectiveFrom);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective From"));
		}

		try {
			Date effectiveTo = DateUtils.stringToDate(locationNaming.getEffectiveTo(), DateUtils.YYYY_MM_DD);
			locationNamingRequest.setEffectiveTo(effectiveTo);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective To"));
		}

		return locationNamingRequest;
	}

	public static List<LogHierarchyRequest> locationLogHierarchyListToLocationLogHierarchyRequestList(List<LogHierarchy> locationHierarchyList) throws ApplicationBusinessException {
		List<LogHierarchyRequest> locationLogHierarchyRequest = new ArrayList<>();

		for (LogHierarchy logHierarchy : locationHierarchyList) {
			locationLogHierarchyRequest.add(locationLogHierarchyToLogHierarchyRequest(logHierarchy));
		}

		return locationLogHierarchyRequest;
	}

	public static LogHierarchyRequest locationLogHierarchyToLogHierarchyRequest(LogHierarchy logHierarchy) throws ApplicationBusinessException {
		LogHierarchyRequest logHierarchyRequest = new LogHierarchyRequest();
		logHierarchyRequest.setParent(logHierarchy.getParent());
		logHierarchyRequest.setVersion(logHierarchy.getVersion());

		try {
			Date effectiveFrom = DateUtils.stringToDate(logHierarchy.getEffectiveFrom(), DateUtils.YYYY_MM_DD);
			logHierarchyRequest.setEffectiveFrom(effectiveFrom);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective From"));
		}

		try {
			Date effectiveTo = DateUtils.stringToDate(logHierarchy.getEffectiveTo(), DateUtils.YYYY_MM_DD);
			logHierarchyRequest.setEffectiveTo(effectiveTo);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective To"));
		}

		return logHierarchyRequest;
	}

	public static List<LocationCsCodeRequest> locationCsCodeListToLocationCsCodeRequestList(List<LocationCsCode> locationCsCodeList) throws ApplicationBusinessException {
		List<LocationCsCodeRequest> locationCsCodeRequestList = new ArrayList<>();
		for (LocationCsCode locationCsCode : locationCsCodeList) {
			locationCsCodeRequestList.add(locationCsCodeToLocationCsCodeRequest(locationCsCode));
		}

		return locationCsCodeRequestList;
	}

	public static LocationCsCodeRequest locationCsCodeToLocationCsCodeRequest(LocationCsCode locationCsCode) throws ApplicationBusinessException {
		LocationCsCodeRequest locationCsCodeRequest = new LocationCsCodeRequest();
		locationCsCodeRequest.setEcosPortCode(Integer.valueOf(locationCsCode.getEcosPortCode()));
		locationCsCodeRequest.setVersion(locationCsCode.getVersion());

		try {
			Date effectiveFrom = DateUtils.stringToDate(locationCsCode.getEffectiveFrom(), DateUtils.YYYY_MM_DD);
			locationCsCodeRequest.setEffectiveFrom(effectiveFrom);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective From"));
		}

		try {
			Date effectiveTo = DateUtils.stringToDate(locationCsCode.getEffectiveTo(), DateUtils.YYYY_MM_DD);
			locationCsCodeRequest.setEffectiveTo(effectiveTo);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective To"));
		}

		return locationCsCodeRequest;
	}

}