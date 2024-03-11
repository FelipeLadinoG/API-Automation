package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.models.Resource;
import com.testing.api.requests.ClientRequest;
import com.testing.api.requests.ResourceRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ResourceSteps {
    private static final Logger logger = LogManager.getLogger(ResourceSteps.class);

    private final ResourceRequest resourceRequest = new ResourceRequest();

    private Response response;
    private Resource resource;
    private List<Resource> resourceList;

    @Given("there are registered resources in the system")
    public void thereAreRegisteredResourcesInTheSystem() {
        response = resourceRequest.getResources();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200, response.getStatusCode());
        resourceList = resourceRequest.getResourcesEntity(response);
        Assert.assertTrue("There arent 5 resources registered",resourceList.size() >= 5);
    }

    @And("I retrieve the details of the latest resource")
    public void iRetrieveTheDetailsOfTheLatestResource() {
        response = resourceRequest.getResource(resourceList.get(resourceList.size() - 1).getId());
        logger.info(response.jsonPath().prettify());
        logger.info("Status code: " + response.getStatusCode());
    }

    @When("I send a GET request to view all the resources")
    public void iSendAGETRequestToViewAllTheResources() {
        response = resourceRequest.getResources();
    }

    @When("I send a PUT request to update the latest resource")
    public void iSendAPUTRequestToUpdateTheLatestResource(String requestBody) {
        resource = resourceRequest.getResourceEntity(requestBody);
        response = resourceRequest.updateResource(resource, resourceList.get(resourceList.size() - 1).getId());
    }

    @Then("the resource response should have a status code of {int}")
    public void theResourceResponseShouldHaveAStatusCodeOf(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the resource response should have the following details:")
    public void theResourceResponseShouldHaveTheFollowingDetails(DataTable expectedData) {
        Resource resource = response.as(Resource.class);
        Map<String, String> expectedDataMap = expectedData.asMaps().get(0);
        Assert.assertEquals(expectedDataMap.get("Name"), resource.getName());
        Assert.assertEquals(expectedDataMap.get("Trademark"), resource.getTrademark());
        Assert.assertEquals(Integer.parseInt(expectedDataMap.get("Stock")), resource.getStock());
        Assert.assertEquals(Double.parseDouble(expectedDataMap.get("Price")), resource.getPrice(), 0.001);
        Assert.assertEquals(expectedDataMap.get("Description"), resource.getDescription());
        Assert.assertEquals(expectedDataMap.get("Tags"), resource.getTags());
    }

    @Then("validates the response with resource JSON schema")
    public void userValidatesResponseWithResourceJSONSchema() {
        String path = "schemas/resourceSchema.json";
        Assert.assertTrue(resourceRequest.validateSchema(response, path));
        logger.info("Schema validation true");
    }

    @Then("validates the response with resource list JSON schema")
    public void userValidatesResponseWithResourceListJSONSchema() {
        String path = "schemas/resourceListSchema.json";
        Assert.assertTrue(resourceRequest.validateSchema(response, path));
        logger.info("Schema valid true for Resource List");
    }

    @When("I send a DELETE request to delete the resource with ID {string}")
    public void iSendADELETERequestToDeleteTheClientWithID(String resourceId) {
        response = resourceRequest.deleteResource(resourceId);
    }
}
