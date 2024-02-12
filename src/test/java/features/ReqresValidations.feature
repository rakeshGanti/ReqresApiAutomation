Feature: Validating Reqres API's

  @AddPlace @Regression

  Scenario Outline: Verify whether Reqres API details are submitted successfully
    Given get the list of users using "GetUserList" api
    Given get the list of users using "GetSingleUser" api
    Given get the list of user for invalid api "GetUserNotFound" api
    Then user create the api with details "<name>" and "<job>" with "AddUserApi" api
    Then user update the api with formated details "<updateName>" and "<updateJob>" using "UpdateUserApi" api
    And  user calls "DeletePlaceAPI" api with check the status code

    Examples:
      | name   | job    | updateName | updateJob |
      | Rakesh | tester | Ramesh     | developer |












