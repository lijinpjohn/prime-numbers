package com.pollinate.primenumbers.stepdef;

import com.pollinate.primenumbers.configuration.SpringIntegrationTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.springframework.http.HttpStatus;


public class StepDefsPrimeNumber extends SpringIntegrationTest {


    @Given("^the client calls /primes/(\\d+)$")
    public void the_client_issues_GET_hello(int initial) throws Throwable {
        executeGet(getEndPoint("/primes/"+initial));
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        final HttpStatus currentStatusCode = latestResponse.getTheResponse().getStatusCode();
        Assert.assertTrue(currentStatusCode.value()==statusCode);
    }

    @Then("the client receives response body {string}")
    public void the_client_receives_response_body(String expectedBody) {
        String actualBody = latestResponse.getBody();
        Assert.assertEquals(expectedBody,actualBody);
    }

}
