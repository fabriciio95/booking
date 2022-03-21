package com.intercab.service.integrator.core.domain.businesspartner.validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.log.core.model.LogMoreDetails;
import com.intercab.service.integrator.core.application.domains.DomainPartnerRole;
import com.intercab.service.integrator.core.application.domains.DomainReturnCode;
import com.intercab.service.integrator.core.application.domains.DomainStatus;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerAddresses;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerAddressesOverviews;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerEcommerceChannels;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerEventHub;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerFiAccounts;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerNames;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerRoles;
import com.intercab.service.integrator.core.domain.businesspartner.modal.BusinessPartnerTaxNumbers;
import com.intercab.service.integrator.core.domain.log.modal.BusinessPartnerLog;
import com.intercab.utils.DateUtils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class BusinessPartnerValidator {
	public static List<DomainPartnerRole> partnerRole(BusinessPartnerEventHub businessPartnerEventHub, BusinessPartnerLog log) throws ApplicationBusinessException {
		List<LogMoreDetails> errorLogList = new ArrayList<LogMoreDetails>();
		List<DomainPartnerRole> partnerRoleList = new ArrayList<DomainPartnerRole>();

		if (businessPartnerEventHub.getBusinessObject().getPartnerRoles() == null || (businessPartnerEventHub.getBusinessObject().getPartnerRoles() != null && businessPartnerEventHub.getBusinessObject().getPartnerRoles().isEmpty())) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Partner Role")));
		} else {
			for (BusinessPartnerRoles businessPartnerRoles : businessPartnerEventHub.getBusinessObject().getPartnerRoles()) {
				try {
					partnerRoleList.add(DomainPartnerRole.valueOf(StringUtils.upperCase(businessPartnerRoles.getPartnerRole())));
				} catch (IllegalArgumentException e) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.INVALID_IDENTIFICATION.getCode(), MessageFormat.format(DomainReturnCode.INVALID_IDENTIFICATION.getDescription(), "Partner Role")));
				}
			}
		}

		if (!errorLogList.isEmpty()) {
			log.setError(errorLogList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}

		return partnerRoleList;
	}

	public static void request(BusinessPartnerEventHub businessPartnerEventHub, BusinessPartnerLog log, Integer countryCod, List<DomainPartnerRole> partnerRoleList) throws ApplicationBusinessException {
		List<LogMoreDetails> errorLogList = new ArrayList<LogMoreDetails>();

		approval(businessPartnerEventHub, countryCod, partnerRoleList, errorLogList);

		if (partnerRoleList == null || (partnerRoleList != null && partnerRoleList.isEmpty())) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.DATA_NOT_BUSINESS_PARTNER.getCode(), DomainReturnCode.DATA_NOT_BUSINESS_PARTNER.getDescription()));
		} else {
			if (businessPartnerEventHub.getBusinessObject().getCode() == null) {
				errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Partner ID")));
			}
	
			requestDataPerson(businessPartnerEventHub.getBusinessObject().getTaxNumbers(), partnerRoleList, errorLogList);
			requestDataFacility(businessPartnerEventHub.getBusinessObject().getAddresses(), errorLogList, countryCod);
			requestDataFiAccounts(businessPartnerEventHub.getBusinessObject().getFiAccounts(), errorLogList);
		}

		if (!errorLogList.isEmpty()) {
			log.setError(errorLogList);
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}
	}

	private static void requestDataPerson(BusinessPartnerTaxNumbers[] taxNumbersArray, List<DomainPartnerRole> partnerRoleList, List<LogMoreDetails> errorLogList) {
		List<BusinessPartnerTaxNumbers> taxNumbersList = Arrays.asList(taxNumbersArray);

		Boolean informedDocument = taxNumbersList.stream().anyMatch(taxId -> StringUtils.isNotBlank(taxId.getTaxCode())
				&& "CNPJ".equals(taxId.getTaxCode().toUpperCase()) && StringUtils.isNotBlank(taxId.getTaxNumber()));

		if (Boolean.FALSE.equals(informedDocument) && partnerRoleList.contains(DomainPartnerRole.CUSTOMER)) {
			informedDocument = taxNumbersList.stream().anyMatch(taxId -> StringUtils.isNotBlank(taxId.getTaxCode())
					&& "CPF".equals(taxId.getTaxCode().toUpperCase()) && StringUtils.isNotBlank(taxId.getTaxNumber()));
		}

		if (Boolean.FALSE.equals(informedDocument)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Tax ID")));
		}

		if (partnerRoleList.contains(DomainPartnerRole.VENDOR)) {
			Boolean hasStateRegistration = taxNumbersList.stream().anyMatch(taxId -> StringUtils.isNotBlank(taxId.getTaxCode())
				&& "STATE REGISTRATION NO".equals(taxId.getTaxCode().toUpperCase())
				&& StringUtils.isNotBlank(taxId.getTaxNumber()));

			if (!hasStateRegistration) {
				errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "State Registration")));
			}
		}
	}

	private static void requestDataFacility(BusinessPartnerAddresses[] addresses, List<LogMoreDetails> errorLogList, Integer countryCod) {
		if (addresses != null && addresses.length > 0) {
			for (int i = 0; i < addresses.length; i++) {
				Boolean informedZipCod = Boolean.FALSE;
				Boolean informedAddressName = Boolean.FALSE;
				Boolean informedAddressNumber = Boolean.FALSE;
				Boolean informedCity = Boolean.FALSE;
				Boolean informedCountry = Boolean.FALSE;
				Boolean informedStatus = Boolean.FALSE;
				Boolean informedExpireDate = Boolean.FALSE;
				Boolean countryIsBrazil = Boolean.FALSE;

				BusinessPartnerAddressesOverviews[] overviews = addresses[i].getAddressOverviews();
				for (int y = 0; y < overviews.length; y++) {
					if (overviews[y].getStreetName1() != null) {
						informedAddressName = Boolean.TRUE;
					}

					if (overviews[y].getStreetNumber() != null) {
						informedAddressNumber = Boolean.TRUE;
					}

					if (overviews[y].getCityNumber() != null) {
						informedCity = Boolean.TRUE;
					}

					if (overviews[y].getPostalCode() != null) {
						informedZipCod = Boolean.TRUE;
					}

					if (overviews[y].getCountryNumber() != null) {
						informedCountry = Boolean.TRUE;
						countryIsBrazil = countryCod.equals(Integer.valueOf(overviews[y].getCountryNumber()));
						countryCod = Integer.valueOf(overviews[y].getCountryNumber());
					}

					if (overviews[y].getStatus() != null) {
						informedStatus = Boolean.TRUE;
					}

					if (overviews[y].getExpiryDate() != null) {
						informedExpireDate = Boolean.TRUE;
						try {
							DateUtils.stringToDate(overviews[y].getExpiryDate(), "YYYY-MM-DD");
						} catch (Exception e) {
							errorLogList.add(new LogMoreDetails(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "Expire Date")));
						}
					}
				}

				if (Boolean.FALSE.equals(informedZipCod)) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Addres ZipCod")));
				}

				if (Boolean.FALSE.equals(informedAddressName)) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Addres Name")));
				}

				if (Boolean.FALSE.equals(informedAddressNumber)) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Addres Number")));
				}

				if (Boolean.FALSE.equals(informedCity)) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Addres City")));
				}

				if (Boolean.FALSE.equals(informedStatus)) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Status")));
				}

				if (Boolean.FALSE.equals(informedExpireDate)) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Expire Date")));
				}

				if (Boolean.FALSE.equals(informedCountry)) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "Addres Country")));
				} else if (Boolean.FALSE.equals(countryIsBrazil)) {
					errorLogList.add(new LogMoreDetails(DomainReturnCode.COUNTRY_COD_IS_NOT_BRAZIL.getCode(), MessageFormat.format(DomainReturnCode.COUNTRY_COD_IS_NOT_BRAZIL.getDescription(), countryCod)));
				}
			}
		}
	}

	private static void requestDataFiAccounts(BusinessPartnerFiAccounts[] fiAccounts, List<LogMoreDetails> errorLogList) {
		Boolean informedTerritoryNumber = Boolean.FALSE;
		Boolean informedFiAccountNumber = Boolean.FALSE;
		Boolean informedExpireDate = Boolean.FALSE;

		if (fiAccounts != null && fiAccounts.length > 0) {
			for (int i = 0; i < fiAccounts.length; i++) {
				if (fiAccounts[i].getTerritoryNumber() != null) {
					informedTerritoryNumber = Boolean.TRUE;
				}

				if (fiAccounts[i].getFiAccountNumber() != null) {
					informedFiAccountNumber = Boolean.TRUE;
				}

				if (fiAccounts[i].getExpiryDate() != null) {
					informedExpireDate = Boolean.TRUE;
					try {
						DateUtils.stringToDate(fiAccounts[i].getExpiryDate(), "YYYY-MM-DD");
					} catch (Exception e) {
						errorLogList.add(new LogMoreDetails(DomainReturnCode.INVALID_DATA.getCode(), MessageFormat.format(DomainReturnCode.INVALID_DATA.getDescription(), "fiAccounts Expire Date")));
					}
				}
			}
		}

		if (Boolean.FALSE.equals(informedTerritoryNumber)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "fiAccounts Territory Number")));
		}

		if (Boolean.FALSE.equals(informedFiAccountNumber)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "fiAccounts Number")));
		}

		if (Boolean.FALSE.equals(informedExpireDate)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "fiAccounts Expire Date")));
		}
	}

	public static void response(HttpURLConnection con, BusinessPartnerLog log) throws IOException, ApplicationBusinessException {
		int responseCode = con.getResponseCode();
		List<LogMoreDetails> logMoreDetailsList = new ArrayList<LogMoreDetails>();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			in.close();

			if (StringUtils.contains(response.toString(), "error")) {
				logMoreDetailsList.add(new LogMoreDetails(String.valueOf(responseCode), response.toString()));
				log.setStatus(DomainStatus.UNPROCESSED.getDescription());
				log.setError(logMoreDetailsList);

				throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
			}
		} else if (responseCode != HttpURLConnection.HTTP_OK) {
			logMoreDetailsList.add(new LogMoreDetails(String.valueOf(responseCode), (con.getResponseMessage() != null) ? con.getResponseMessage() : getErrorResponseBodyMessage(con)));
			log.setStatus(DomainStatus.UNPROCESSED.getDescription());
			log.setError(logMoreDetailsList);

			throw new ApplicationBusinessException(String.valueOf(DomainStatus.UNPROCESSED.getCode()));
		}
	}

	public static String getErrorResponseBodyMessage(HttpURLConnection con) {
		if (con.getErrorStream() == null) {
			return StringUtils.EMPTY;
		}

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		StringBuilder sbErrorResponseBodyMessage = new StringBuilder();

		try {
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				sbErrorResponseBodyMessage.append(line);
			}
		} catch (IOException e) {
			// No implementation
		}

		return sbErrorResponseBodyMessage.toString();
	}

	private static void approval(BusinessPartnerEventHub businessPartnerEventHub, Integer countryCod, List<DomainPartnerRole> partnerRoleList, List<LogMoreDetails> errorLogList) {
		Boolean isApproved = Boolean.FALSE;

		if (ArrayUtils.isNotEmpty(businessPartnerEventHub.getBusinessObject().getPartnerNames())) {
			for (BusinessPartnerNames businessPartnerNames : businessPartnerEventHub.getBusinessObject().getPartnerNames()) {
				if (verifyApprovalStatuAndExpiryDateIsValid(businessPartnerNames)) {
					isApproved = Boolean.TRUE;
					break;
				}
			}
		}

		if (Boolean.FALSE.equals(isApproved)) {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.INVALID_IDENTIFICATION.getCode(), MessageFormat.format(DomainReturnCode.INVALID_IDENTIFICATION.getDescription(), "Partner Names - Expiry Date")));
		} else
			if (ArrayUtils.isNotEmpty(businessPartnerEventHub.getBusinessObject().getAddresses())) {
				for (BusinessPartnerAddresses businessPartnerAddresses : businessPartnerEventHub.getBusinessObject().getAddresses()) {
					if (ArrayUtils.isNotEmpty(businessPartnerAddresses.getAddressOverviews())) {
						BusinessPartnerAddressesOverviews[] overviews = businessPartnerAddresses.getAddressOverviews();
						for (BusinessPartnerAddressesOverviews businessPartnerAddressesOverviews : overviews) {
							if (Boolean.TRUE.equals("APPROVED".equals(businessPartnerAddressesOverviews.getStatus())) && Boolean.FALSE.equals(countryCod.equals(Integer.valueOf(businessPartnerAddressesOverviews.getCountryNumber())))) {
								isApproved = Boolean.FALSE;
								break;
							}
						}
					}
				}
			}

		if (Boolean.TRUE.equals(isApproved) && ArrayUtils.isNotEmpty(businessPartnerEventHub.getBusinessObject().getFiAccounts())) {
			Boolean territoryNumberIsBrazil = Boolean.FALSE;

			for (BusinessPartnerFiAccounts businessPartnerFiAccounts : businessPartnerEventHub.getBusinessObject().getFiAccounts()) {
				if (businessPartnerFiAccounts.getTerritoryNumber() != null && (
						(partnerRoleList.contains(DomainPartnerRole.CUSTOMER) && Boolean.TRUE.equals("BRAZIL".equals(businessPartnerFiAccounts.getTerritoryNumber())))
						|| (partnerRoleList.contains(DomainPartnerRole.VENDOR) && Boolean.TRUE.equals("CORPORATE_BRAZIL".equals(businessPartnerFiAccounts.getTerritoryNumber()))))) {
					territoryNumberIsBrazil = Boolean.TRUE;
				}
			}

			if (Boolean.FALSE.equals(territoryNumberIsBrazil)) {
				errorLogList.add(new LogMoreDetails(DomainReturnCode.TERRITORY_NUMBER_INVALID.getCode(), DomainReturnCode.TERRITORY_NUMBER_INVALID.getDescription()));
			}
		} else {
			errorLogList.add(new LogMoreDetails(DomainReturnCode.EMPTY_ATTRIBUTE.getCode(), MessageFormat.format(DomainReturnCode.EMPTY_ATTRIBUTE.getDescription(), "FiAccounts")));
		}
	}

	private static boolean verifyApprovalStatuAndExpiryDateIsValid(BusinessPartnerNames businessPartnerNames) {
		Date expiryDate = null;

		try {
			expiryDate = StringUtils.isNotBlank(businessPartnerNames.getExpiryDate()) ? DateUtils.stringToDate(businessPartnerNames.getExpiryDate(), DateUtils.YYYY_MM_DD) : null;
		} catch (ParseException e) {
			// Treatment done in the Validation layer
		}
		
		return "APPROVED".equals(businessPartnerNames.getApprovalStatus()) && expiryDate != null && (DateUtils.validateMinorOrGreaterThan(expiryDate, new Date()) == -1);
	}
}