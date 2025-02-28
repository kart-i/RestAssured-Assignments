Feature: Create and validate new change request 

Background: 

		Given the base spec
    And   the authentication process 
 
Scenario Outline: Create a change request and validate the status code

    When 	a post request is sent with "<Short Description>" and <Description>
    Then  validate the status code is 201
    And   validate the description and short description in the response body
    And   validate the status line is "HTTP/1.1 201 Created" in the response
   
Examples:

	| Short Description | Description |
	| Created change request|Created change request with Description|
	
	

		
		