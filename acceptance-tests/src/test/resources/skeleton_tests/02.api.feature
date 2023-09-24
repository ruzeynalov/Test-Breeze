@API
Feature: User management. Get users information from Service

  @SeverityLevel.NORMAL
  @tmsLink=DEMO-4
  @api
  @reqres
  @AC6-DEMO9
  Scenario: 02.01.01 Users management. Get single user
    Given API request for user with id equals 2 is sent to web service
    And The status code is 200
    Then response contain user with first name equal to 'fuchsia rose'