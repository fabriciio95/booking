package com.intercab.service.integrator.core.service.token;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercab.exception.core.ApplicationBusinessException;
import com.intercab.service.integrator.core.application.domains.DomainReturnCode;

@Service
public class TokenService implements ITokenService {
	@Value("${microsoft.token.url}")
	private String URL;

	@Value("${microsoft.client.id}")
	private String CLIENT_ID;

	@Value("${microsoft.client.secret}")
	private String CLIENT_SECRET;
	
	@Value("${microsoft.client.scope}")
	private String CLIENT_SCOPE;

	@Override
	public String getToken() throws ApplicationBusinessException {
		try {
			URI uri = new URI(URL);

			MultiValueMap<String, String> body= new LinkedMultiValueMap<String, String>();
			body.add("grant_type", "client_credentials");
			body.add("client_id", CLIENT_ID);
			body.add("client_secret", CLIENT_SECRET);
			body.add("scope", CLIENT_SCOPE);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body,headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, httpEntity, String.class);

			if (ObjectUtils.allNotNull(responseEntity, responseEntity.getBody())) {
				TypeReference<HashMap<String,String>> typeRef = new TypeReference<HashMap<String,String>>() {};
				HashMap<String,String> map = new ObjectMapper().readValue(responseEntity.getBody(), typeRef);
				String tokenType = map.get("token_type");
				String accessToken = map.get("access_token");
				return tokenType + StringUtils.SPACE + accessToken;
			}

			return StringUtils.EMPTY;
		} catch (HttpClientErrorException error) {
			throw new ApplicationBusinessException(error.getLocalizedMessage());
		} catch (RestClientException | URISyntaxException error) {
			throw new ApplicationBusinessException(DomainReturnCode.FAILURE_COMUNICATION_GET_TOKEN_MICROSOFT.getDescription());
		} catch (JsonProcessingException e ) {
			throw new ApplicationBusinessException(e.getLocalizedMessage());
		}
	}
}