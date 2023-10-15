package kirimaru.e2e;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

import kirimaru.restapi.AnimalsRestController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("動かなくなっている")
public class RestAssuredTests {

  @Test
  void test_01() {
    given()
        .standaloneSetup(new AnimalsRestController())
        .param("piyo", "aiueo")
        .when()
        .get("/animals")
        .then()
        .statusCode(200)
        .body(equalTo("hogehoge"))
//        .body("hogehoge", equalTo("hogehoge"))
//        .body("content", equalTo("Hello, Johan!"));
    ;
  }
}
