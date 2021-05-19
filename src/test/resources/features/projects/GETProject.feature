Feature: Get Project

  In order to get a project in Pivotal
  Authenticated as valid Pivotal API consumer

  Background: Set authentication
    Given the user sets valid authentication headers

  @functional @createProject @deleteProject
  Scenario: Retrieve existing project (1100)
    When the user sends a GET request to "/projects/{id}"
    Then verifies response should have the 200 status code
    And verifies response body should match with "projects/getProjectResponse.json" JSON schema
    And verifies response contain the following values
      | id               | {id}    |
      | name             | {name}  |
      | kind             | project |
      | version          | 1       |
      | iteration_length | 1       |
