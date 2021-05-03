package theScoreDemo;


import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

//Created by: Sean Xu
//This RunWithCucumber class will run the cucumber step definitions, generate the cucumber report.
//Please do make sure the directory of the features folder is correct on you computer before executing

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/java/features", glue = {"StepDefinitions"}, plugin = {"html:target/cucumber-reports/cucumber.html", "pretty"})
public class RunWithCucumber {
}
