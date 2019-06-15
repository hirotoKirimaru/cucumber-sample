package features;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberHooks {

    @Before
    public void before(Scenario scenario) {
    }

    @After
    public void after(Scenario scenario) {
        System.out.println("After:" + scenario.getName());
    }
}
