package features;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginSteps {


//    @Given("topページを表示する")
//    public void topページを表示する(String string) {
//        Selenide.open("/");
//    }

    @Then("topページを表示する")
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

    @When("{string}を選択する")
    public void を選択する(String string) {
        Selenide.$(By.xpath("//a[text()='" + string + "']")).click();
    }

    @Then("{string}を表示する")
    public void を表示する(String string) {

//        assertEquals(WebDriverRunner.url(), true);
        assertEquals(WebDriverRunner.url().contains(string), true);

    }
}
