package com.aarushi.automation.api.base;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class BaseService {

    protected RequestSpecification requestSpec;
    private Map<String, String> headers = new HashMap<>();

    public BaseService() {
        this.requestSpec = RestClientFactory.getRequestSpec();
        this.headers.putAll(RestClientFactory.defaultHeaders());
    }

    public void setBaseUri(String baseUri) {
        requestSpec.baseUri(baseUri);
    }

    public void setBasePath(String basePath) {
        requestSpec.basePath(basePath);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
        requestSpec.headers(headers);
    }

    public void addHeaders(Map<String, String> customHeaders) {
        headers.putAll(customHeaders);
        requestSpec.headers(headers);
    }

    public void setBody(Object body) {
        requestSpec.body(body);
    }

    public void addPathParam(String name, Object value) {
        requestSpec.pathParam(name, value);
    }

    public void addPathParams(Map<String, ?> params) {
        requestSpec.pathParams(params);
    }

    public void addQueryParam(String name, Object value) {
        requestSpec.queryParam(name, value);
    }

    public void setContentType(String contentType) {
        requestSpec.contentType(contentType);
    }

    public void setBasicAuth(String username, String password) {
        requestSpec.auth().preemptive().basic(username, password);
    }

    public void setBearerToken(String token) {
        requestSpec.auth().oauth2(token);
    }

    public Response get() {
        return io.restassured.RestAssured
                .given()
                .spec(requestSpec)
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public Response post() {
        return io.restassured.RestAssured
                .given()
                .spec(requestSpec)
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public Response put(String endpoint) {
        return io.restassured.RestAssured
                .given()
                .spec(requestSpec)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    public Response delete(String endpoint) {
        return io.restassured.RestAssured
                .given()
                .spec(requestSpec)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    // Can add more common methods like queryParams, pathParams, auth etc.
}