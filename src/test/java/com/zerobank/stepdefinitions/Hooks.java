package com.zerobank.stepdefinitions;


import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before(order = 0)
    public void setUp() {
        Driver.get().manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }

    @After
    public void TearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        BrowserUtils.waitFor(2);
        Driver.closeDriver();
    }

    @Before(value = "@database", order = 1)      // normal testten önce çalışmasını beklediğim methodu çalıştırır
    public void openDataBase() {
        System.out.println("opening database...");
    }

    @After("@database")
    public void closeDataBase() {
        System.out.println("closing database...");
    }

}
