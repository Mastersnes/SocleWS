package com.bebel.soclews.abstrait;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Base aux clients REST
 */
public abstract class AbstractClient {
    protected RestTemplate restTemplate = new RestTemplate();
    protected HttpHeaders headers;
    protected String baseUrl;

    protected <RESPONSE> void checkResponse(final ResponseEntity<RESPONSE> response) {
        if (response != null && response.getStatusCode() != null) {
            if (response.getStatusCode().is3xxRedirection() && response.getStatusCode() != HttpStatus.NOT_MODIFIED) {
                throw new HttpClientErrorException(response.getStatusCode(), "Erreur de type 3xx");
            } else if (response.getStatusCode().is4xxClientError()) {
                throw new HttpClientErrorException(response.getStatusCode(), "Erreur de type 4xx");
            } else if (response.getStatusCode().is5xxServerError()) {
                throw new HttpServerErrorException(response.getStatusCode(), "Erreur de type 5xx");
            }
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Erreur - la reponse est nulle");
        }
    }

    protected void checkParams() {
        if (StringUtils.isEmpty(this.baseUrl)) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Erreur, la méthode d'initialisation n'a pas été appelé");
        } else if (this.headers == null) this.initHeader();
    }

    /**
     * Appel le endpoint indiqué
     */
    public <RESPONSE, REQUEST> RESPONSE perform(final String endPoint, final REQUEST request, final HttpMethod method, final Class<RESPONSE> responseType) throws HttpStatusCodeException {
        System.out.println("Appel du service REST : " + this.baseUrl + endPoint + " en mode " + method);
        this.checkParams();
        final ResponseEntity<RESPONSE> response = this.restTemplate.exchange(this.baseUrl + endPoint, method, this.header(request), responseType, new Object[0]);
        this.checkResponse(response);
        return response.getBody();
    }

    /**
     * Appel le endpoint avec une com.bebel.soclews.response sous forme de liste
     */
    public <RESPONSE, REQUEST> RESPONSE perform(final String endPoint, final REQUEST request, final HttpMethod method, final ParameterizedTypeReference<RESPONSE> responseType) throws HttpStatusCodeException {
        System.out.println("Appel du service REST : " + this.baseUrl + endPoint + " en mode " + method + " avec une com.bebel.soclews.response sous forme de liste");
        this.checkParams();
        ResponseEntity<RESPONSE> response = this.restTemplate.exchange(this.baseUrl + endPoint, method, this.header(request), responseType, new Object[0]);
        this.checkResponse(response);
        return response.getBody();
    }

    public <RESPONSE, REQUEST> RESPONSE post(String endPoint, REQUEST request, Class<RESPONSE> responseType) {
        return this.perform(endPoint, request, HttpMethod.POST, responseType);
    }

    public <RESPONSE, REQUEST> RESPONSE post(String endPoint, REQUEST request, ParameterizedTypeReference<RESPONSE> responseType) {
        return this.perform(endPoint, request, HttpMethod.POST, responseType);
    }

    public <RESPONSE, REQUEST> RESPONSE get(String endPoint, REQUEST request, Class<RESPONSE> responseType) {
        return this.perform(endPoint, request, HttpMethod.GET, responseType);
    }

    public <RESPONSE, REQUEST> RESPONSE get(String endPoint, REQUEST request, ParameterizedTypeReference<RESPONSE> responseType) {
        return this.perform(endPoint, request, HttpMethod.GET, responseType);
    }

    public <RESPONSE, REQUEST> RESPONSE delete(String endPoint, REQUEST request, Class<RESPONSE> responseType) {
        return this.perform(endPoint, request, HttpMethod.DELETE, responseType);
    }

    public <RESPONSE, REQUEST> RESPONSE delete(String endPoint, REQUEST request, ParameterizedTypeReference<RESPONSE> responseType) {
        return this.perform(endPoint, request, HttpMethod.DELETE, responseType);
    }

    public <RESPONSE, REQUEST> RESPONSE put(String endPoint, REQUEST request, Class<RESPONSE> responseType) {
        return this.perform(endPoint, request, HttpMethod.PUT, responseType);
    }

    public <RESPONSE, REQUEST> RESPONSE put(String endPoint, REQUEST request, ParameterizedTypeReference<RESPONSE> responseType) {
        return this.perform(endPoint, request, HttpMethod.PUT, responseType);
    }

    protected void init(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    protected HttpHeaders initHeader() {
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return this.headers;
    }

    protected <REQUEST> HttpEntity<REQUEST> header(REQUEST request) {
        return new HttpEntity(request, this.headers);
    }
}
