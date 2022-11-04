package kirimaru.e2e.apitest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import kirimaru.e2e.helper.ApiTestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AnimalApi_patch extends ApiTestTemplate {

  private String PATH = "animals";
  // TODO: 受け取れるJSONではない。
  String body = """
        {
          "animal":{
            "id": "1",
            "registerDate": "2022-10-10 01:22:23.2123"
          }
        }
        """;
  @Test
  void test_01() {
    ResponseEntity<String> response = patch(PATH, body);

    assertAll(
        () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
        () -> assertThat(response.getBody()).isEqualTo("patch")
    );
  }

  @Test
  void test_02() {
    String response = patch2(PATH, body);

    assertAll(
//        () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
//        () -> assertThat(response.getBody()).isEqualTo("patch")
        () -> assertThat(response).isEqualTo("patch")
    );
  }

  @Test
  void test_XX() {
    ResponseEntity<String> response = post(PATH, body);

    assertAll(
        () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
        () -> assertThat(response.getBody()).isEqualTo("hogehoge")
    );
  }

  @Test
  void test_X2() {
    ResponseEntity<String> response = post2(PATH, body);

    assertAll(
        () -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
        () -> assertThat(response.getBody()).isEqualTo("hogehoge")
    );
  }
}
