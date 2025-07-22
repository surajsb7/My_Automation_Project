package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", 
				glue = "StepDef", 
				tags ="",
				plugin = {"pretty","html:target/html/report.html"}, 
				monochrome = true,
				publish=true,
				dryRun=false)

public class TestRunner {

}
