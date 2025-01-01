package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/features/CreateChangeRequest.feature"},
        glue = {"steps"},
        dryRun = true,
        publish = true,
		monochrome = true
	)
//        
  

public class TestNGrunner extends AbstractTestNGCucumberTests {
	
}
