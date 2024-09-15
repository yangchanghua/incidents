Feature: Incident API E2E Tests

  Background:
    * def baseUrl = 'http://localhost:8080/api/incidents'
    Given url baseUrl
    When method get
    Then status 200
    * def incidentId = response.content[0].id

  Scenario: Create a new incident
    Given url baseUrl
    And request { title: 'Server Outage', description: 'Server was down for 2 hours', status: 'Open' }
    When method post
    Then status 200
    And match response.title == 'Server Outage'
    And match response.status == 'Open'

  Scenario Outline: Get paginated incidents with title filter
    Given url baseUrl
    And param title = <title>
    And param page = 0
    And param size = 2
    When method get
    Then status 200
    And match response.pageable.pageNumber == 0
    And match response.pageable.pageSize == 2
    And assert response.totalElements >= 0
    And assert response.totalPages >= 0
    And assert response.content.length <= 2
    Examples:
      | title     |
      | ''        |
      | 'Server'  |


  Scenario: Get incident by ID
    Given url baseUrl + '/' + incidentId
    When method get
    Then status 200
    And match response.id == incidentId


  Scenario: Update the incident
    Given url baseUrl + '/' + incidentId
    And request { title: 'Service Down', description: 'Issue resolved after 2 hours', status: 'Resolved' }
    When method patch
    Then status 200
    And match response.title == 'Service Down'
    And match response.status == 'Resolved'

  Scenario: Delete the incident
    Given url baseUrl + '/' + incidentId
    When method delete
    Then status 200

  Scenario: Try to get the non-existing incident
    Given url baseUrl + '/' + '9999999'
    When method get
    Then status 404
