package kirimaru.e2e;

import kirimaru.restapi.AnimalsRestController;
import org.junit.jupiter.api.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

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
