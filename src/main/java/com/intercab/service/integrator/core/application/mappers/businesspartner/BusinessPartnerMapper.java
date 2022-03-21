package com.intercab.service.integrator.core.application.mappers.businesspartner;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainAttributesConstants;
import com.intercab.service.integrator.core.application.domains.DomainPartnerCode;
import com.intercab.service.integrator.core.application.domains.DomainPartnerRole;
import com.intercab.service.integrator.core.application.mappers.DataMapper;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerAddresses;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerAddressesOverviews;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerAddressesRequest;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerEventHub;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerFiAccounts;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerFunctions;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerNames;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerRequest;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerRoles;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerTaxNumbers;
import com.intercab.service.integrator.core.domain.businesspartner.modal.GlobeStatus;
import com.intercab.service.integrator.core.domain.log.modal.BusinessPartnerLog;
import com.intercab.utils.DateUtils;

import org.apache.commons.lang3.StringUtils;

public class BusinessPartnerMapper {
	public static BusinessPartnerRequest createRequestFromServiceBus(BusinessPartnerEventHub businessPartner, Integer countryCod, DomainPartnerRole partnerRole) {
		BusinessPartnerRequest request = new BusinessPartnerRequest();
		String expiryDate = getExpiryDatePartnerFunctions(businessPartner, request, partnerRole);

		getGlobeStatus(businessPartner, request, partnerRole, expiryDate);
		getCurrentVersionPerson(request, businessPartner.getBusinessObject().getPartnerNames(), businessPartner.getBusinessObject().getTaxNumbers());
		getCurrentVersionFacility(request, businessPartner.getBusinessObject().getAddresses(), businessPartner.getBusinessObject().getFiAccounts(), countryCod, expiryDate, partnerRole);
		getVendoBusiness(request, businessPartner.getBusinessObject().getPartnerRoles(), partnerRole);

		request.setBusinessPartnerId(Long.valueOf(businessPartner.getBusinessObject().getCode()));
		request.setProcessData(DataMapper.createProcessDataFromRequest(businessPartner.getProcessData()));

		return request;
	}

	private static String getExpiryDatePartnerFunctions(BusinessPartnerEventHub businessPartner, BusinessPartnerRequest request, DomainPartnerRole currentPartnerRole) {
		List<BusinessPartnerRoles> partnerRoles = businessPartner.getBusinessObject().getPartnerRoles();
		Optional<BusinessPartnerRoles> businessPartnerRole = partnerRoles.stream().filter(partnerRole -> currentPartnerRole.toString().equals(partnerRole.getPartnerRole())).findFirst();

		if (businessPartnerRole.isPresent()) {
			boolean hasActiveFunction = businessPartnerRole.get().getPartnerFunctions().stream().filter(businessPartnerFunction -> isActiveExpiryDate(businessPartnerFunction.getExpiryDate())).count() > 0;

			Optional<BusinessPartnerFunctions> businessPartnerFunctionsApproved = Optional.empty();
			if (hasActiveFunction) {
				businessPartnerFunctionsApproved = businessPartnerRole.get().getPartnerFunctions().stream().filter(businessPartnerFunction -> isActiveExpiryDate(businessPartnerFunction.getExpiryDate())).findFirst();
			} else if (businessPartnerRole.get().getPartnerFunctions() != null && !businessPartnerRole.get().getPartnerFunctions().isEmpty()){
				businessPartnerFunctionsApproved = Optional.of(businessPartnerRole.get().getPartnerFunctions().get(businessPartnerRole.get().getPartnerFunctions().size() - 1));
			}

			DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			if (businessPartnerFunctionsApproved.isPresent()) {
				LocalDate expiryDate = LocalDate.from(sdf.parse(businessPartnerFunctionsApproved.get().getExpiryDate()));

				if (expiryDate.isBefore(LocalDate.now())) {
					return sdf.format(expiryDate);
				}
			}
		}

		return null;
	}

	private static void getGlobeStatus(BusinessPartnerEventHub businessPartner, BusinessPartnerRequest request, DomainPartnerRole partnerRole, String expiryDate) {
		if (DomainPartnerRole.VENDOR.equals(partnerRole)) {
			List<BusinessPartnerRoles> partnerRoles = businessPartner.getBusinessObject().getPartnerRoles();
			Optional<BusinessPartnerRoles> businessPartnerRole = partnerRoles.stream().filter(partnerRoleFromList -> partnerRole.toString().equals(partnerRoleFromList.getPartnerRole())).findFirst();

			if (businessPartnerRole.isPresent()) {
				Optional<BusinessPartnerFunctions> businessPartnerFunction = businessPartnerRole.get().getPartnerFunctions().stream().filter(partnerFunction -> isActiveExpiryDate(partnerFunction.getExpiryDate())).findFirst();

				if (businessPartnerFunction.isPresent()) {
					GlobeStatus globeStatus = GlobeStatus.valueOf(businessPartnerFunction.get().getApprovalStatus());

					if (globeStatus != null) {
						request.setGlobeStatusDescription(globeStatus.getDescription());
					}
				}
			}
		}
	}

	private static void getCurrentVersionPerson(BusinessPartnerRequest request, BusinessPartnerNames[] namesArray, BusinessPartnerTaxNumbers[] taxNumbersArray) {
		Long stateRegistrationVersion = 0L;
		Long stateRegistrationExemptVersion = 0L;
		Long cnpjVersion = 0L;
		Long cpfVersion = 0L;

		for (int i = 0; i < namesArray.length; i++) {
			Date expiryDate = null;

			try {
				expiryDate = StringUtils.isNotBlank(namesArray[i].getExpiryDate()) ? DateUtils.stringToDate(namesArray[i].getExpiryDate(), DateUtils.YYYY_MM_DD) : null;
			} catch (ParseException e) {
				// Treatment done in the Validation layer
			}

			if (namesArray[i] != null && DomainAttributesConstants.APPROVED.getDescription().equals(namesArray[i].getApprovalStatus()) && expiryDate != null && (DateUtils.validateMinorOrGreaterThan(expiryDate, new Date()) == -1)) {
				String companyName = StringUtils.join(new String[] {namesArray[i].getPartnerName1(), namesArray[i].getPartnerName2(), namesArray[i].getPartnerName3()}, StringUtils.SPACE);
				request.setCompanyName(StringUtils.trim(companyName));
			}
		}

		for (int i = 0; i < taxNumbersArray.length; i++) {
			if (taxNumbersArray[i] != null) {
				if (taxNumbersArray[i].getTaxCode().contains(DomainAttributesConstants.STATE_REGISTRATION_NO.getDescription()) && (taxNumbersArray[i].getVersion() > stateRegistrationVersion)) {
					if (StringUtils.isNumeric(taxNumbersArray[i].getTaxNumber())) {
						stateRegistrationVersion = taxNumbersArray[i].getVersion();
						request.setStateRegistration(taxNumbersArray[i].getTaxNumber());
					} else {
						stateRegistrationExemptVersion = taxNumbersArray[i].getVersion();
						request.setStateRegistrationExempt(taxNumbersArray[i].getTaxNumber().equals(DomainAttributesConstants.ISENTO.getDescription()) ? true : false);
					}
				}

				if (taxNumbersArray[i].getTaxCode().equals(DomainAttributesConstants.CNPJ.getDescription()) && (taxNumbersArray[i].getVersion() > cnpjVersion)) {
					cnpjVersion = taxNumbersArray[i].getVersion();
					request.setTaxId(taxNumbersArray[i].getTaxNumber());
					request.setPersonType(DomainAttributesConstants.LE.getDescription());
				} else if (taxNumbersArray[i].getTaxCode().equals(DomainAttributesConstants.CPF.getDescription()) && (taxNumbersArray[i].getVersion() > cpfVersion)) {
					cpfVersion = taxNumbersArray[i].getVersion();
					request.setTaxId(taxNumbersArray[i].getTaxNumber());
					request.setPersonType(DomainAttributesConstants.P.getDescription());
				}
			}
		}

		if (stateRegistrationVersion == 0L && stateRegistrationExemptVersion == 0L) {
			request.setStateRegistrationExempt(Boolean.TRUE.booleanValue());
		} else if (stateRegistrationVersion > 0L && stateRegistrationExemptVersion == 0L) {
			request.setStateRegistrationExempt(Boolean.FALSE.booleanValue());
		}
	}

	private static void getCurrentVersionFacility(BusinessPartnerRequest request, BusinessPartnerAddresses[] addresses, BusinessPartnerFiAccounts[] fiAccounts, Integer countryCod, String strExpiryDate, DomainPartnerRole partnerRole) {
		if (addresses != null && addresses.length > 0) {
			BusinessPartnerAddressesRequest[] facilityList = new BusinessPartnerAddressesRequest[addresses.length];

			for (int i = 0; i < addresses.length; i++) {
				BusinessPartnerAddressesOverviews[] overviews = addresses[i].getAddressOverviews();

				for (int y = 0; y < overviews.length; y++) {
					Date expiryDate = null;

					try {
						expiryDate = StringUtils.isNotBlank(strExpiryDate) ? DateUtils.stringToDate(strExpiryDate, DateUtils.YYYY_MM_DD) : DateUtils.stringToDate(overviews[y].getExpiryDate(), DateUtils.YYYY_MM_DD);
					} catch (ParseException e) {
						// Treatment done in the Validation layer
					}

					if (DomainAttributesConstants.APPROVED.getDescription().equals(overviews[y].getStatus()) && expiryDate != null && !expiryDate.before(DateUtils.removeHour(new Date()))) {
						BusinessPartnerAddressesRequest facility = new BusinessPartnerAddressesRequest();

						Map<String, String> fiAccountMap = getCurrentVersionFiAccounts(fiAccounts, countryCod, partnerRole);

						facility.setBusuCityId(Long.valueOf(overviews[y].getCityNumber()));
						facility.setBusuCityName(overviews[y].getMlCityName());
						facility.setBusuStateId(Long.valueOf(overviews[y].getStateNumber()));
						facility.setAddress(StringUtils.trim(overviews[y].getStreetName1()));
						facility.setNumber(overviews[y].getStreetNumber());
						facility.setZipCode(overviews[y].getPostalCode());

						if (Boolean.TRUE.equals(fiAccountMap.containsKey(DomainAttributesConstants.EXPIRY_DATE.getDescription()))) {
							facility.setGlobeExpiryDate(fiAccountMap.get(DomainAttributesConstants.EXPIRY_DATE.getDescription()));
						}

						if (Boolean.TRUE.equals(fiAccountMap.containsKey(DomainAttributesConstants.GLOBE_SFC.getDescription()))) {
							facility.setGlobeSfc(fiAccountMap.get(DomainAttributesConstants.GLOBE_SFC.getDescription()));
						}

						facility.setGlobeBPnumber(request.getBusinessPartnerId());
						facility.setGlobeStatusDescription(overviews[y].getStatus());
						facility.setDistrict(overviews[y].getDistrict());
						facility.setComplement(getCurrentFacilityComplement(overviews[y]));

						if (DomainPartnerRole.VENDOR.equals(partnerRole)) {
							facility.setSource(DomainAttributesConstants.GLOBE_BP.getDescription());
						}

						facilityList[i] = facility;
					}
				}
			}

			request.setFacilityList(facilityList);
		}
	}

	private static boolean isActiveExpiryDate(String globeExpiryDate) {
		Date expiryDate = null;

		try {
			expiryDate = DateUtils.stringToDate(StringUtils.isNotBlank(globeExpiryDate) ? globeExpiryDate : null, DateUtils.YYYY_MM_DD);
		} catch (ParseException e) {
			// No implementation
		}

		return expiryDate != null && !expiryDate.before(DateUtils.removeHour(new Date()));
	}

	private static void getVendoBusiness(BusinessPartnerRequest request, List<BusinessPartnerRoles> partnerRoles, DomainPartnerRole partnerRole) {
		if (DomainPartnerRole.VENDOR.equals(partnerRole) && (partnerRoles != null && !partnerRoles.isEmpty())) {
			List<Long> vendorBusinessList = new ArrayList<Long>();

			partnerRoles.forEach(parnerRole -> {
				if (parnerRole.getPartnerFunctions() != null && !parnerRole.getPartnerFunctions().isEmpty()) {
					parnerRole.getPartnerFunctions().forEach(parnerFunction -> {
						Long idBusiness = DomainPartnerCode.idToCode(parnerFunction.getFunctionTypeCode());
						if (idBusiness != null) {
							vendorBusinessList.add(idBusiness);
						}
					});
				}
			});

			request.setVendorBusinessList(vendorBusinessList.stream().toArray(Long[]::new));
		}
	}

	private static String getCurrentFacilityComplement(BusinessPartnerAddressesOverviews overviews) {
		String complemento = StringUtils.EMPTY;

		if (StringUtils.isNotBlank(overviews.getStreetName2())) {
			complemento = overviews.getStreetName2();
		}

		return complemento;
	}

	private static Map<String, String> getCurrentVersionFiAccounts(BusinessPartnerFiAccounts[] fiAccounts, Integer countryCod, DomainPartnerRole partnerRole) {
		Map<String, String> fiAccountMap = new HashMap<String, String>();
		fiAccountMap.put(DomainAttributesConstants.GLOBE_SFC.getDescription(), null);
		fiAccountMap.put(DomainAttributesConstants.EXPIRY_DATE.getDescription(), null);

		if (fiAccounts != null && fiAccounts.length > 0) {
			for (int i = 0; i < fiAccounts.length; i++) {
				if (fiAccounts[i].getPartnerRole().equals(partnerRole.toString())) {
					Date expiryDate = null;

					try {
						expiryDate = StringUtils.isNotBlank(fiAccounts[i].getExpiryDate()) ? DateUtils.stringToDate(fiAccounts[i].getExpiryDate(), DateUtils.YYYY_MM_DD) : null;
					} catch (ParseException e) {
						// Treatment done in the Validation layer
					}

					if (!(DateUtils.validateMinorOrGreaterThan(expiryDate, DateUtils.removeHour(new Date())) == 1)) {
						StringBuilder strBuilder = new StringBuilder();

						if ((DomainAttributesConstants.BRAZIL.getDescription()).equals(fiAccounts[i].getTerritoryNumber())) {
							strBuilder.append(countryCod);
						} else
							if (DomainPartnerRole.CUSTOMER.equals(partnerRole)) {
								strBuilder.append(DomainPartnerRole.CUSTOMER.getCode());
							} else if (DomainPartnerRole.VENDOR.equals(partnerRole) && (DomainAttributesConstants.CORPORATE_BRAZIL.getDescription()).equals(fiAccounts[i].getTerritoryNumber())) {
								strBuilder.append(DomainPartnerRole.VENDOR.getCode());
							}

						strBuilder.append(fiAccounts[i].getFiAccountNumber());

						fiAccountMap.replace(DomainAttributesConstants.GLOBE_SFC.getDescription(), strBuilder.toString());
					}

					fiAccountMap.replace(DomainAttributesConstants.EXPIRY_DATE.getDescription(), fiAccounts[i].getExpiryDate());
				}
			}
		}

		return fiAccountMap;
	}

	@SuppressWarnings("unchecked")
	public static List<LogMoreDetails> createMoreDetails(BusinessPartnerLog businessPartnerLog) {
		Gson gson = new Gson();

		List<LogMoreDetails> list = new ArrayList<LogMoreDetails>();
		list.addAll((Collection<? extends LogMoreDetails>) businessPartnerLog.getError());

		if (businessPartnerLog.getData() instanceof String) {
			list.add(new LogMoreDetails(DomainAttributesConstants.DATA.getDescription(), businessPartnerLog.getData().toString()));
		} else {
			list.add(new LogMoreDetails(DomainAttributesConstants.DATA.getDescription(), gson.toJson(businessPartnerLog.getData())));
		}

		return list;
	}
}