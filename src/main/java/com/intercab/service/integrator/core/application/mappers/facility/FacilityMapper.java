package com.intercab.service.integrator.core.application.mappers.facility;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.service.integrator.core.application.domains.DomainReturnCode;
import com.intercab.service.integrator.core.domain.facility.modal.Facility;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityAccess;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityChronology;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityDetails;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityEventHub;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityFunction;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityHour;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityLogHierarchy;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityNaming;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityPartner;
import com.intercab.service.integrator.core.domain.facility.modal.FacilityStatus;
import com.intercab.service.integrator.core.domain.facility.request.FacilityAccessRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityChronologyRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityDetailsRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityFunctionRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityHourRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityLogHierarchyRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityNamingRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityPartnerRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityRequest;
import com.intercab.service.integrator.core.domain.facility.request.FacilityStatusRequest;
import com.intercab.utils.DateUtils;

public class FacilityMapper {

	public static FacilityRequest createRequestFromFacility(FacilityEventHub facilityEventHub) throws ApplicationBusinessException {
		Facility facility = facilityEventHub.getBusinessObject();
		FacilityRequest request = new FacilityRequest();

		request.setGlobeFacilityCode(facility.getCode());
		request.setGlobeCustomerFacility(facility.isCustomerFacility());
		request.setChronologies(toChronologyRequestList(facility.getChronology()));
		request.setFacilityStatus(toFacilityStatusRequestList(facility.getFacilityStatus()));
		request.setFacilityDetails(toFacilityDetailsRequest(facility.getFacilityDetails()));

		return request;
	}

	public static List<FacilityChronologyRequest> toChronologyRequestList(List<FacilityChronology> facilityChronologyList) throws ApplicationBusinessException {
		List<FacilityChronologyRequest> facilityChronologyRequestList = new ArrayList<>();

		for (FacilityChronology facilityChronology : facilityChronologyList) {
			facilityChronologyRequestList.add(toChronologytRequest(facilityChronology));
		}

		return facilityChronologyRequestList;
	}

	public static FacilityChronologyRequest toChronologytRequest(FacilityChronology facilityChronology) throws ApplicationBusinessException {
		FacilityChronologyRequest facilityChornologyRequest = new FacilityChronologyRequest();

		try {
			Date effectiveFrom = DateUtils.stringToDate(facilityChronology.getEffectiveFrom(), DateUtils.YYYY_MM_DD);
			facilityChornologyRequest.setEffectiveFrom(effectiveFrom);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective From"));
		}

		try {
			Date effectiveTo = DateUtils.stringToDate(facilityChronology.getEffectiveTo(), DateUtils.YYYY_MM_DD);
			facilityChornologyRequest.setEffectiveTo(effectiveTo);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective To"));
		}

		facilityChornologyRequest.setVersion(facilityChronology.getVersion());
		facilityChornologyRequest.setLogHierarchy(tologHierarchyRequest(facilityChronology.getLogHierarchy()));
		facilityChornologyRequest.setNaming(toFacilityNamingRequest(facilityChronology.getNaming()));
		facilityChornologyRequest.setPartner(toFacilityPartnerRequest(facilityChronology.getPartner()));

		return facilityChornologyRequest;
	}

	public static FacilityLogHierarchyRequest tologHierarchyRequest(FacilityLogHierarchy facilityLogHierarchy) {
		FacilityLogHierarchyRequest facilityLogHierarchyRequest = new FacilityLogHierarchyRequest();
		facilityLogHierarchyRequest.setParent(facilityLogHierarchy.getParent());

		return facilityLogHierarchyRequest;
	}

	public static FacilityNamingRequest toFacilityNamingRequest(FacilityNaming facilityNaming) {
		FacilityNamingRequest facilityNamingRequest = new FacilityNamingRequest();
		facilityNamingRequest.setCode(facilityNaming.getCode());
		facilityNamingRequest.setName(facilityNaming.getName());

		return facilityNamingRequest;
	}

	public static FacilityPartnerRequest toFacilityPartnerRequest(FacilityPartner facilityPartner) {
		FacilityPartnerRequest facilityPartnerRequest = new FacilityPartnerRequest();
		facilityPartnerRequest.setPartnerCode(facilityPartner.getPartnerCode());
		facilityPartnerRequest.setPartnerNumber(facilityPartner.getPartnerNumber());

		return facilityPartnerRequest;
	}

	public static List<FacilityStatusRequest> toFacilityStatusRequestList(List<FacilityStatus> facilityStatusList) throws ApplicationBusinessException {
		List<FacilityStatusRequest> facilityStatusRequestList = new ArrayList<>();

		for (FacilityStatus facilityStatus : facilityStatusList) {
			facilityStatusRequestList.add(toFacilityStatusRequest(facilityStatus));
		}

		return facilityStatusRequestList;
	}

	public static FacilityStatusRequest toFacilityStatusRequest(FacilityStatus facilityStatus) throws ApplicationBusinessException {
		FacilityStatusRequest facilityStatusRequest = new FacilityStatusRequest();
		facilityStatusRequest.setStatus(facilityStatus.getStatus());

		try {
			Date effectiveFrom = DateUtils.stringToDate(facilityStatus.getEffectiveFrom(), DateUtils.YYYY_MM_DD);
			facilityStatusRequest.setEffectiveFrom(effectiveFrom);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective From"));
		}

		try {
			Date effectiveTo = DateUtils.stringToDate(facilityStatus.getEffectiveTo(), DateUtils.YYYY_MM_DD);
			facilityStatusRequest.setEffectiveTo(effectiveTo);
		} catch (ParseException e) {
			throw new ApplicationBusinessException(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Effective To"));
		}

		facilityStatusRequest.setVersion(facilityStatus.getVersion());

		return facilityStatusRequest;
	}

	public static FacilityDetailsRequest toFacilityDetailsRequest(FacilityDetails facilityDetails) {
		FacilityDetailsRequest facilityDetailsRequest = new FacilityDetailsRequest();
		facilityDetailsRequest.setAccess(toFacilityAccessRequestList(facilityDetails.getAccess()));
		facilityDetailsRequest.setFacilityHour(toFacilityHourRequestList(facilityDetails.getFacilityHour()));
		facilityDetailsRequest.setFunction(toFacilityFunctionRequestList(facilityDetails.getFunction()));

		return facilityDetailsRequest;
	}

	public static List<FacilityAccessRequest> toFacilityAccessRequestList(List<FacilityAccess> facilityAccessList) {
		List<FacilityAccessRequest> facilityAccessRequestList = new ArrayList<>();

		if (facilityAccessList == null) {
			return facilityAccessRequestList;
		}

		for (FacilityAccess facilityAccess : facilityAccessList) {
			facilityAccessRequestList.add(toFacilityAccessRequest(facilityAccess));
		}

		return facilityAccessRequestList;
	}

	public static FacilityAccessRequest toFacilityAccessRequest(FacilityAccess facilityAccess) {
		FacilityAccessRequest facilityAccessRequest = new FacilityAccessRequest();

		if (Objects.nonNull(facilityAccess)) {
			facilityAccessRequest.setAccessMode(facilityAccess.getAccessMode());
			facilityAccessRequest.setVersion(facilityAccess.getVersion());
		}

		return facilityAccessRequest;
	}

	public static List<FacilityHourRequest> toFacilityHourRequestList(List<FacilityHour> facilityHourList) {
		List<FacilityHourRequest> facilityHourRequestList = new ArrayList<>();

		if (facilityHourList == null) {
			return facilityHourRequestList;
		}

		for (FacilityHour facilityHour : facilityHourList) {
			facilityHourRequestList.add(toFacilityHourRequest(facilityHour));
		}

		return facilityHourRequestList;
	}

	public static FacilityHourRequest toFacilityHourRequest(FacilityHour facilityHour) {
		FacilityHourRequest facilityAccessRequest = new FacilityHourRequest();

		if (Objects.nonNull(facilityHour)) {
			facilityAccessRequest.setFacilityHourType(facilityHour.getType());
			facilityAccessRequest.setVersion(facilityHour.getVersion());
		}

		return facilityAccessRequest;
	}

	public static List<FacilityFunctionRequest> toFacilityFunctionRequestList(List<FacilityFunction> facilityFunctionList) {
		List<FacilityFunctionRequest> facilityFunctionRequestList = new ArrayList<>();

		if (facilityFunctionList == null) {
			return facilityFunctionRequestList;
		}

		for (FacilityFunction facilityFunction : facilityFunctionList) {
			facilityFunctionRequestList.add(toFacilityFunctionRequest(facilityFunction));
		}

		return facilityFunctionRequestList;
	}

	public static FacilityFunctionRequest toFacilityFunctionRequest(FacilityFunction facilityFunction) {
		FacilityFunctionRequest facilityFunctionRequest = new FacilityFunctionRequest();

		if (Objects.nonNull(facilityFunction)) {
			facilityFunctionRequest.setFacilityFunctionType(facilityFunction.getFacilityFunctionType());
			facilityFunctionRequest.setVersion(facilityFunction.getVersion());
		}

		return facilityFunctionRequest;
	}
}