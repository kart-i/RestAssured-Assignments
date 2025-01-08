Feature: Create and validate new change request 

Background: 

		Given the base spec
    And   the authentication process 
 
Scenario Outline: Create a change request and validate the status code

    When 	a post request is sent with <Short Description>  and <Description>
    Then  validate the status code is 201
    And   validate the description and short description in the response body
    And   validate the status line is "HTTP/1.1 201 Created" in the response
   
Examples:

	| Short Description | Description |
	| Created change request|Created change request with Description|
	
	
Scenario: Read a change request and validate

	  When the get request is sent with the "cafc8c88c30f12101f4bb92ed40131cb"
	  Then validate the status code is 200 
	  And  validate the response with sysId "cafc8c88c30f12101f4bb92ed40131cb"
	  And  validate the status line is "HTTP/1.1 200 OK" in the response
	  
	
Scenario Outline: Update a change request and validate

		When  the put request is sent with "<Short Description>","<Description>" and "cafc8c88c30f12101f4bb92ed40131cb"
		Then  validate the status code is 200 
		And   validate the updated description and short description in the response body
		And   validate the status line is "HTTP/1.1 200 OK" in the response
		
Examples:

	| Short Description | Description |
	| Updated change request|Updated change request with Description|			
		
Scenario: Delete a change request and validate

		When  the delete request is sent with "cafc8c88c30f12101f4bb92ed40131cb"
		Then  validate the status code is 204 
		And   validate the status line is "HTTP/1.1 204 No Content" in the response
		
		