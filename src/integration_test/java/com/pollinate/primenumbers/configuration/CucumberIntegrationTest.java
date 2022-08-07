package com.pollinate.primenumbers.configuration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/integration_test/resources/features"},
glue = {"com.pollinate.primenumbers.configuration",
        "com.pollinate.primenumbers.stepdef",
        "com.pollinate.primenumbers.utils"
        },
monochrome = true
)
public class CucumberIntegrationTest extends SpringIntegrationTest {
}