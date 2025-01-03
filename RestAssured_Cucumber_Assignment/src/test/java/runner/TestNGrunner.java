package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/features/CreateChangeRequest.feature:28"},
        glue = {"steps"},
        dryRun = false,
        publish = true,
		monochrome = true
	)
       
public class TestNGrunner extends AbstractTestNGCucumberTests {
	
}
