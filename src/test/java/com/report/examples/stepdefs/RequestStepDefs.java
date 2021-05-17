package com.report.examples.stepdefs;

import com.report.examples.client.RequestManager;
import com.report.examples.config.PivotalCfg;
import com.report.examples.context.Context;
import com.report.examples.utils.AuthenticationUtils;
import com.report.examples.utils.JsonSchemaValidator;
import com.report.examples.utils.Mapper;
import com.report.examples.utils.ResponseBodyValidator;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.IOException;
import java.util.Map;

public class RequestStepDefs {

    private final Context context;
    private Response response;
    private String jsonData = "";

    /**
     * Constructor of Request Step Definitions.
     *
     * @param contextToSet Context to set
     */
    public RequestStepDefs(final Context contextToSet) {
        this.context = contextToSet;
    }

    /**
     * Sets valid authentication headers.
     */
    @Given("the user sets valid authentication headers")
    public void setsValidAuthentication() {
        RequestManager.setRequestSpec(AuthenticationUtils.getLoggedReqSpec());
    }

    /**
     * Sends a GET request.
     *
     * @param endpoint resource endpoint
     */
    @When("the user sends a GET request to {string}")
    public void sendsGETRequest(final String endpoint) {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        response = RequestManager.get(endpointMapped);
    }

    /**
     * Stores values given in Json data.
     *
     * @param jsonDataParameter String json
     */
    @Given("the user sets the following values in the Json data")
    public void theFollowingValuesInTheJsonData(final String jsonDataParameter) {
        jsonData = jsonDataParameter;
    }

    /**
     * Sends a POST request.
     *
     * @param endpoint resource endpoint
     */
    @When("the user sends POST request to {string}")
    public void theUserSendsAPOSTRequestTo(final String endpoint) throws IOException {
        String endpointMapped = Mapper.mapValue(endpoint, context.getData());
        response = RequestManager.post(endpointMapped, jsonData);
        context.saveData(response.asString());
    }

    /**
     * Verifies status code.
     *
     * @param expectedStatusCode Int status code
     */
    @Then("verifies response should have the {int} status code")
    public void verifiesStatusCode(final int expectedStatusCode) {
        Assert.assertEquals(response.statusCode(), expectedStatusCode);
    }

    /**
     * Verifies response body with json schema.
     *
     * @param schemaPath schema path
     */
    @And("verifies response body should match with {string} JSON schema")
    public void verifiesResponseBodyJSONSchema(final String schemaPath) {
        JsonSchemaValidator.validate(response, PivotalCfg.getInstance().getSchemasPath() + schemaPath);
    }

    /**
     * Verifies response body values.
     *
     * @param expectedValues expected response values
     */
    @And("verifies response contain the following values")
    public void verifiesResponseBody(final Map<String, String> expectedValues) throws IOException {
        ResponseBodyValidator.validateBody(response, context.getData(), expectedValues);
    }
}
