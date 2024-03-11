@active
Feature: Resource testing CRUD

  @smokeTest
  Scenario: View all the resources
    Given there are registered resources in the system
    When I send a GET request to view all the resources
    Then the resource response should have a status code of 200
    And validates the response with resource list JSON schema

  @smokeTest
  Scenario: Update last resource details
    Given there are registered resources in the system
    And I retrieve the details of the latest resource
    When I send a PUT request to update the latest resource
    """
    {
        "name": "New Resource Name",
        "trademark": "New Trademark",
        "stock": 1000,
        "price": 99.99,
        "description": "This is a description",
        "tags": "tag1",
        "active": true
    }
    """
    Then the resource response should have a status code of 200
    And the resource response should have the following details:
      | Name             | Trademark    | Stock | Price | Description          | Tags | Active |
      | New Resource Name| New Trademark| 1000  | 99.99 | This is a description| tag1 | true   |
    And validates the response with resource JSON schema
