package features;

import com.codeborne.selenide.Selenide;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import features.component.SelenideBuilder;

public class CucumberHooks {
    @Before
    public void before(Scenario scenario) {
        SelenideBuilder.builder();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            Selenide.screenshot(scenario.getName());
        }
    }
}
