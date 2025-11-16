package com.aarushi.automation.api.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import com.aarushi.automation.api.listeners.ExtentLoggingFilter;

import java.util.HashMap;
import java.util.Map;

public class RestClientFactory {

    // ThreadLocal to ensure each thread has its own RequestSpecification
    private static final ThreadLocal<RequestSpecification> threadLocalSpec = ThreadLocal.withInitial(() ->
            new RequestSpecBuilder()
                    .addFilter(new ExtentLoggingFilter())      // Logs to ExtentReports
                    .addFilter(new RequestLoggingFilter())     // Console logging
                    .addFilter(new ResponseLoggingFilter())    // Console logging
                    .build()
    );

    public static RequestSpecification getRequestSpec() {
        return threadLocalSpec.get();
    }

    public static void remove() {
        threadLocalSpec.remove();
    }

    /**
     * Utility to create default headers per thread
     */
    public static Map<String, String> defaultHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
}
