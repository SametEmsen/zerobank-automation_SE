package com.zerobank.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "json:target/cucumber.json",
                "html:target/default-html-reports.html",
                "pretty"},
        features = "src/test/resources/features",
        glue = "com/zerobank/stepdefinitions",
        dryRun = false,
        tags = "@regression"
)
public class CukesRunner {

}
