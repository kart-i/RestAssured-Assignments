package steps;

import org.hamcrest.Matchers;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.ChangeRequestPayload;


public class CreateChangeRequestTestSteps {

	RequestSpecification requestSpecification;
	Response response;
	
	ChangeRequestPayload payload = new ChangeRequestPayload();

	@Given("the Endpoint")
	public void setEndPoint() {
		requestSpecification = new RequestSpecBuilder().setBaseUri("https://dev182968.service-now.com")
				.setBasePath("/api/now/table/change_request")
				.addHeader("Content-Type", "application/json")
				.build();	
	}
	
	@Given ("the authentication process")
	public void setAuthProcess() {
		requestSpecification = RestAssured.given()
				.spec(requestSpecification)
				.auth().basic("admin","9Qn=DQ7hvfE!");
			
	}
	
	@When("/^the payload is added with (.*) and (.*)$/")
	public void setPayload(String shortDesc, String Desc) {
		
		payload.setShort_description(shortDesc);
		payload.setDescription(Desc);
		
		requestSpecification = requestSpecification.body(payload);
						
	}

	@And ("a post request is sent")
	public void validateReponse() {

		response = requestSpecification.post();
	
	}
	
	@Then("/^validate the status code is (.*)$/")
	public void validate_the_status_code_is(Integer code) {
		Assert.assertEquals(code,response.statusCode());
	}
	
	@Then("validate the description and short description in the response body")
	public void validate_the_description_and_short_description_in_the_response_body() {
	    
		String desc = response.jsonPath().getString("result.description");
		String shortDec = response.jsonPath().getString("result.short_description");
		
		Assert.assertEquals(desc, payload.getDescription());
		Assert.assertEquals(shortDec, payload.getShort_description());
	}
	
	


}
