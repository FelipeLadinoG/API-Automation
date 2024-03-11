@active
Feature: Client testing CRUD

  @smokeTest
  Scenario: Create a new client
    Given I have a client with the following details:
      | Name   | LastName | Country | City | Email        | Phone        |
      | LeBron | James    | USA     | LA   | lb@email.com | 300-0000-0000|
    When I send a POST request to create a client
    Then the response should have a status code of 201
    And the response should include the details of the created client
    And validates the response with client JSON schema

  @smokeTest
  Scenario: View all the clients
    Given there are registered clients in the system
    When I send a GET request to view all the clients
    Then the response should have a status code of 200
    And validates the response with client list JSON schema

