package steps;


import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojos.PayloadForCreatingRecord;
import pojos.PayloadForUpdatingRecord;


public class ChangeRequestTestSteps {
	
	private static final String BASE_URI= "https://dev291587.service-now.com/api/now/table/change_request";
	
	RequestSpecification requestSpecification;
	Response response;
	
	String extractedSysId;
	
	
	PayloadForCreatingRecord payload = new PayloadForCreatingRecord();
	PayloadForUpdatingRecord updatePayload = new PayloadForUpdatingRecord();
	
	@Given("the base spec")
	public void setEndPoint() {
		
		requestSpecification = RestAssured.given()
				   						  .baseUri(BASE_URI)
				   						  .header("Content-Type", "application/json");		
	}
	
	@Given("the authentication process")
	public void setAuthProcess() {
		
		requestSpecification = requestSpecification.auth()
												   .basic("admin","0umYUd@8DIf@");				
	}
	
	@When("/^a post request is sent with (.*) and (.*)$/")
	public void sendRequest(String shortDesc, String desc) {
		
		payload.setShort_description(shortDesc);
		payload.setDescription(desc);
		
		
		response = requestSpecification.when()
									   .body(payload)
				                       .post();		
	}
	
	@When("the get request is sent with the {string}")
	public void the_get_request_is_sent_with_the_sys_id(String sysId) {	
		
		response =  requestSpecification.basePath("/{sys_id}")
				 						.pathParam("sys_id",sysId)
				 						.when()			 						
				 						.get();
				 						 
		System.out.println(response.prettyPrint());
		 
	}
	
	@When("the put request is sent with {string},{string} and {string}")
	public void the_put_request_is_sent_with_updated_change_request_updated_change_request_with_description_and(String shortDesc,String desc,String sysId) {
	   
		updatePayload.setShort_description(shortDesc);
		updatePayload.setDescription(desc);
		
	    response = requestSpecification.basePath("/{sys_id}")
	    							   .pathParam("sys_id",sysId)
	    							   .when()
	    							   .body(updatePayload)
	    							   .put();
	}
	
	@When("the delete request is sent with {string}")
	public void the_delete_request_is_sent(String sysId) {
	   
		response = requestSpecification.basePath("/{sys_id}")
				                       .pathParam("sys_id",sysId)
				                       .when()
				                       .delete();			                       			
	}
	
	@Then("validate the response with sysId {string}")
	public void validate_the_response_with_sysId(String sysId) {
		  
	extractedSysId = response.jsonPath().getString("result.sys_id");
	Assert.assertEquals(extractedSysId, sysId);
		
	}
	
	@Then("validate the description and short description in the response body")
	public void validate_the_description_and_short_description_in_the_response_body() {
		
		String desc = response.jsonPath().getString("result.description");
		String shortDesc = response.jsonPath().getString("result.short_description");
		
		Assert.assertEquals(payload.getDescription(),desc);
		Assert.assertEquals(payload.getShort_description(),shortDesc);
		
	}
	
	@Then("validate the updated description and short description in the response body")
	public void validate_the_update_description_and_short_description_in_the_response_body() {
		
		String desc = response.jsonPath().getString("result.description");
		String shortDesc = response.jsonPath().getString("result.short_description");
		
		Assert.assertEquals(updatePayload.getDescription(),desc);
		Assert.assertEquals(updatePayload.getShort_description(),shortDesc);
		
		
	}
	
	@Then("validate the status code is {int}")
	public void validate_the_status_code_is(Integer code) {
		Assert.assertEquals(response.statusCode(),code);
			
	}
	
	@Then("validate the status line is {string} in the response")
	public void validate_the_status_line(String statusLine) {
		
		Assert.assertEquals(response.getStatusLine(),statusLine);
		
	}
	

}
