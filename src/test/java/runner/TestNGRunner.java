package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/features", glue = "stepdefintion"
, monochrome = true, plugin = {"html:reports/cucumber.html"},tags= "@QA1")
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
