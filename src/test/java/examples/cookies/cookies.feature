Feature: sample karate test script to reproduce delete cookie issue

  - Set a cookie (myCookie=myValue)
  - List it to show it is present in subsequent requests
  - Delete it (overwrite it with a dummy value, with max-age=0)
  - List to show what cookies remain

  Expected : in the last List call, no cookies should be present
  Actual : Header is still sent (myCookie=myValue)

  Scenario: Should delete cookies

     # Setting the cookie
    Given url demoBaseUrl
    And path '/set'
    When method GET
    Then status 200

    * def cookie = responseHeaders["Set-Cookie"][0]
    * print cookie

    # Deleting the cookie
    Given url demoBaseUrl
    And path '/delete'
    When method GET
    Then status 200
    # The request should contain the cookie previously set as it has not yet been deleted
    * def sentCookie = karate.prevRequest.headers.Cookie[0]
    * print sentCookie
    And match sentCookie contains cookie

    # Accessing another well-known to check what cookies remain
    Given url demoBaseUrl
    And path '/list'
    When method GET
    Then status 200
    # The request should not contain
    * def sentCookie = karate.prevRequest.headers.Cookie[0]
    * print sentCookie
    And match sentCookie !contains cookie
