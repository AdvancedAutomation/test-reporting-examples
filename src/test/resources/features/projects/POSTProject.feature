Feature: Post Project

  In order to post a project in Pivotal
  Authenticated as valid Pivotal API consumer

  Background: Set authentication
    Given the user sets valid authentication headers

  @functional @deleteProject
  Scenario: Create a new project
    Given the user sets the following values in the Json data
    """
    {
      "name": "pre-demo"
    }
    """
    When the user sends POST request to "/projects"
    Then verifies response should have the 204 status code
    And verifies response body should match with "projects/postProjectResponse.json" JSON schema
    And verifies response contain the following values
      | kind             | project      |
      | name             | pre-demo     |
      | version          | 1            |
      | iteration_length | 1            |
