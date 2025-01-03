package steps;

import org.hamcrest.MatcherAssert;
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
import pojos.PayloadForCreatingRecord;
import pojos.PayloadForUpdatingRecord;


public class CreateChangeRequestTestSteps {
	
	private static final String BASE_URI= "https://dev182968.service-now.com/api/now/table/change_request";
	

	RequestSpecification baseSpec;
	RequestSpecification requestSpecWithAuth;
	RequestSpecification requestSpectWithPayload;
	RequestSpecification request;
	RequestSpecification requestSpecWithUpdatePayload;
	
	Response response;
	Response singleChangeRequestRecord;
	
	
	String sysId = "ac1e5eb683f29610eb071c29feaad3c3";
	String extractedSysId;
	
	
	PayloadForCreatingRecord payload = new PayloadForCreatingRecord();
	PayloadForUpdatingRecord updatePayload = new PayloadForUpdatingRecord();
	
	@Given("the base spec")
	public void setEndPoint() {
		baseSpec = new RequestSpecBuilder().setBaseUri(BASE_URI)
				                           .addHeader("Content-Type", "application/json")
				                           .build();	
	}
	
	@Given ("the authentication process")
	public void setAuthProcess() {
		requestSpecWithAuth = RestAssured.given()
				              .spec(baseSpec)
				              .auth().basic("admin","9Qn=DQ7hvfE!");
				
	}
	
	@When("/^the payload is added with (.*) and (.*)$/")
	public void setPayload(String shortDesc, String Desc) {	
		payload.setShort_description(shortDesc);
		payload.setDescription(Desc);
		
		requestSpectWithPayload = RestAssured.given()
				   				  .spec(requestSpecWithAuth)
				   				  .body(payload);
						
	}

	@And ("a post request is sent")
	public void sendRequest() {
		response = requestSpectWithPayload.post();
	     
	}
	
	@Then("validate the status code is {int}")
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
	
	@And("the Endpoint with {string}")
	public void the_endpoint_is_added_with_sys_id(String sysId) {
		request = RestAssured.given()
							 .spec(requestSpecWithAuth)
							 .basePath("/{sys_id}")
							 .pathParam("sys_id",sysId).log().all();
		
	}
	
	@When("the get request is sent with the sysId")
	public void the_get_request_is_sent_with_the_sys_id() {	   
		 singleChangeRequestRecord = request.get();
		 System.out.println(singleChangeRequestRecord.prettyPrint());
		 
	}
	
	@Then("validate the response")
	public void validate_the_response() {
		  
	extractedSysId = singleChangeRequestRecord.jsonPath().getString("result.sys_id");
	
	Assert.assertEquals(extractedSysId, sysId);
		
	}
	
	@When("/^the payload is updated with (.*) and (.*)$/")
	public void updateWithValue(String shortDesc, String Desc) {	
		updatePayload.setShort_description(shortDesc);
		updatePayload.setDescription(Desc);
		
		requestSpecWithUpdatePayload = RestAssured.given()
				   				                  .spec(request)
				   				                  .body(updatePayload);
						
	}
	
	@Then("validate the updated description and short description in the response body")
	public void validate_the_update_description_and_short_description_in_the_response_body() {
		
		String desc = response.jsonPath().getString("result.description");
		String shortDec = response.jsonPath().getString("result.short_description");
		
		Assert.assertEquals(desc, updatePayload.getDescription());
		Assert.assertEquals(shortDec, updatePayload.getShort_description());
		
	}
	
	@When("the put request is sent")
	public void the_put_request_is_sent() {
	    response = requestSpecWithUpdatePayload.put();
	    System.out.println(response.prettyPrint());
	}
	
	@When("the delete request is sent")
	public void the_delete_request_is_sent() {
	   
		response = request.delete();
		
	}
	@Then("validate the deleted reponse")
	public void validate_the_deleted_reponse() {
		MatcherAssert.assertThat(response.getStatusCode(), Matchers.equalTo(204));
		MatcherAssert.assertThat(response.getStatusLine(), Matchers.equalToIgnoringCase("HTTP/1.1 204 No Content"));		
	}
	

}
