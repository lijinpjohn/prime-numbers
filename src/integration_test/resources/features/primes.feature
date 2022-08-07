Feature: The message can be retrieved
  Scenario Outline: client makes call to GET /primes
    When the client calls /primes/<initial>
    Then the client receives status code of <statusCode>
    And the client receives response body <expectedBody>

    Examples:
      | initial | statusCode | expectedBody |
      | 10      | 202        | "{\"initial\":10,\"primes\":[2,3,5,7]}" |
      | 2       | 202        | "{\"initial\":2,\"primes\":[2]}" |
      | 0       | 400        | "[\"No prime numbers to show for the inōitial provided\"]" |




