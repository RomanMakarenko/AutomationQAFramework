package romm.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/romm/cucumber", glue = "romm/stepDefinitions",
        monochrome = true, plugin = {"html:target/cucumberReport.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
}
