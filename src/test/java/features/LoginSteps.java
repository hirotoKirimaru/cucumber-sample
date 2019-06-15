package features;

import com.codeborne.selenide.Selenide;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {

    @Then("{string}ページを表示する")
    public void ページを表示する(String string) {
        assertEquals("jest_practice", Selenide.title());
    }

    @Given("topページに遷移する")
    public void topページに遷移する() {
        Selenide.open("/");
    }

    @When("ログインボタンを押す")
    public void ログインボタンを押す() {
        Selenide.$("#login").click();
    }

    @When("ログアウトボタンを押す")
    public void ログアウトボタンを押す() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}
