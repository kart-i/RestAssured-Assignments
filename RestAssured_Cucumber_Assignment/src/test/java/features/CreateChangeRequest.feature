
Feature: Create and validate new change request 
  
 Scenario Outline: Create a change request and validate the status code
    
    Given the Endpoint
    And   the authentication process
    When  the payload is added with <Short Description> and <Description>
    And   a post request is sent
    Then  validate the status code is <Status code>
    And   validate the description and short description in the response body
   
Examples:
	| Short Description | Description | Status code | 
	| Created change request| Created change request with Description|201|
	