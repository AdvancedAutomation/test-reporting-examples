package com.report.examples.runner;

import com.report.examples.config.PivotalCfg;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Cucumber TestNG runner class.
 */
@CucumberOptions(
        plugin = {"pretty"},
        features = {"src/test/resources/features"},
        glue = {"com.report.examples"}
)
public final class Runner extends AbstractTestNGCucumberTests {

    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    /**
     * {@inheritDoc}
     */
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    /**
     * Executes code before all scenarios.
     */
    @BeforeTest
    public void beforeAllScenarios() {
        System.setProperty("dataproviderthreadcount", PivotalCfg.getInstance().getCucumberThreadCount());
    }

    /**
     * Executes code before all scenarios.
     */
    @AfterTest
    public void copyAllureFiles() {
        var allureSource = new File("src/test/resources/allure.properties");
        var allureDest = new File("build/allure-results/allure.properties");
        var catSource = new File("src/test/resources/categories.json");
        var catDest = new File("build/allure-results/categories.json");
        var envSource = new File("src/test/resources/environment.properties");
        var envDest = new File("build/allure-results/environment.properties");
        try {
            Files.copy(allureSource.toPath(), allureDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(catSource.toPath(), catDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(envSource.toPath(), envDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            LOGGER.error("Error when copy allure files.");
            LOGGER.error(e.getMessage());
        }
    }
}


