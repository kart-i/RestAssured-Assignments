Feature: Create and validate new change request 

Background: 

		Given the base spec
    And   the authentication process 
 
Scenario Outline: Create a change request and validate the status code

    When 	a post request is sent with <Short Description>  and <Description>
    Then  validate the status code is 201
    And   validate the description and short description in the response body
   
Examples:

	| Short Description | Description |
	| Created change request|Created change request with Description|
	
	
Scenario: Read a change request and validate

	  When the get request is sent with the "f53fadb7c37212101f4bb92ed4013108"
	  Then validate the status code is 200 
	  And  validate the response with sysId "f53fadb7c37212101f4bb92ed4013108"
	  
	
Scenario Outline: Update a change request and validate

		When  the put request is sent with <Short Description>,<Description> and "f53fadb7c37212101f4bb92ed4013108"
		Then  validate the status code is 200 
		And   validate the updated description and short description in the response body
		
Examples:

	| Short Description | Description |
	| Updated change request|Updated change request with Description|			
		

Scenario: Delete a change request and validate

		When  the delete request is sent with "f53fadb7c37212101f4bb92ed4013108"
		Then  validate the status code is 204 
		And   validate the deleted reponse
		
		