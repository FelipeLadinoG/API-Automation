@active
Feature: Resource testing CRUD

  @smokeTest
  Scenario: View all the resources
    Given there are registered resources in the system
    When I send a GET request to view all the resources
    Then the resource response should have a status code of 200
    And validates the response with resource list JSON schema

  @smokeTest
  Scenario: Update resource details
    Given there are registered resources in the system
    And I retrieve the details of the latest resource
    When I send a PUT request to update the latest resource
    """
    {
        "name": "Convertible Car",
        "trademark": "Juan",
        "stock": 1000,
        "price": 99.99,
        "description": "This is a description",
        "tags": "auto",
        "active": true
    }
    """
    Then the resource response should have a status code of 200
    And the resource response should have the following details:
      | Name  | Trademark | Stock | Price | Description  | Tags | Active |
      | Convertible Car | Juan    | 1000 | 99.99   | This is a description |auto  | true  |
    And validates the response with resource JSON schema
