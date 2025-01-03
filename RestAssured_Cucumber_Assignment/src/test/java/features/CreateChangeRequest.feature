Feature: Create and validate new change request 

Background: 

		Given the base spec
    And   the authentication process 
 
Scenario Outline: Create a change request and validate the status code

    When  the payload is added with <Short Description> and <Description>
    And   a post request is sent
    Then  validate the status code is 201
    And   validate the description and short description in the response body
   
Examples:

	| Short Description | Description |
	| Created change request|Created change request with Description|
	
	
Scenario: Read a change request and validate

		And  the Endpoint with "ac1e5eb683f29610eb071c29feaad3c3"
	  When the get request is sent with the sysId
	  Then validate the response
	   
	
Scenario Outline: Update a change request and validate

		And   the Endpoint with "ac1e5eb683f29610eb071c29feaad3c3"
		And   the payload is updated with <Short Description> and <Description>
		When  the put request is sent
		Then  validate the updated description and short description in the response body
		
Examples:

	| Short Description | Description |
	| Updated change request|Updated change request with Description|			
		

Scenario: Delete a change request and validate

		And   the Endpoint with "ac1e5eb683f29610eb071c29feaad3c3"
		When  the delete request is sent
		Then  validate the deleted reponse
		
		