package com.report.examples.utils;

import com.report.examples.config.PivotalCfg;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public final class AuthenticationUtils {

    private static final String TOKEN_HEADER = "X-TrackerToken";
    private static final String BASE_URL_API = PivotalCfg.getInstance().getBaseUrl();
    private static RequestSpecification requestSpecification;

    /**
     * Constructor for AuthenticationUtils.
     */
    private AuthenticationUtils() {

    }

    /**
     * Getter to request specifications with
     * Token of user and the BaseURI of API which is loaded
     * from properties file.
     *
     * @return requestSpecification
     */
    public static RequestSpecification getLoggedReqSpec() {
        RestAssured.baseURI = BASE_URL_API;
        requestSpecification = new RequestSpecBuilder()
            .setRelaxedHTTPSValidation()
            .addHeader(TOKEN_HEADER, PivotalCfg.getInstance().getToken())
            .addHeader("Content-Type", "application/json")
            .build();
        return requestSpecification;
    }

    /**
     * Getter to request specifications with
     * Token of user and the BaseURI of API which is loaded
     * from properties file.
     *
     * @return requestSpecification
     */
    public static RequestSpecification getLoggedReqSpecWithoutContentType() {
        RestAssured.baseURI = BASE_URL_API;
        requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .addHeader(TOKEN_HEADER, PivotalCfg.getInstance().getToken())
                .build();
        return requestSpecification;
    }

    /**
     * Getter to request specifications.
     *
     * @return requestSpecification
     */
    public static RequestSpecification getNotLoggedReqSpec() {
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL_API);
        return requestSpecification;
    }
}
