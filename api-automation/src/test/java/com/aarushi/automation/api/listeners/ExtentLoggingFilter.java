
package com.aarushi.automation.api.listeners;


import com.aarushi.automation.common.reporting.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ExtentLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);
        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {
            test.log(Status.INFO, "**Request Endpoint:** " + requestSpec.getMethod() + " " + requestSpec.getURI());
            test.log(Status.INFO, "**Request Headers:**\n" + requestSpec.getHeaders().toString());

            if (requestSpec.getBody() != null) {
                test.log(Status.INFO, "**Request Body:**\n" + requestSpec.getBody());
            }

            test.log(Status.INFO, "**Response Status:** " + response.getStatusCode());
            test.log(Status.INFO, "**Response Headers:**\n" + response.getHeaders().toString());
            test.log(Status.INFO, "**Response Body:**\n" + response.getBody().prettyPrint());
        }

        return response;
    }
}

