import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SeleniumConfigTests {
  @Test
  void test_01() {

    assertThat(SeleniumConfig.getProperty("baseUrl")).isEqualTo("http://localhost:8080");
  }

  @Test
  void test_01_01() {
    assertThat(SeleniumConfig.getProperty("noKey")).isEqualTo("");
  }

  @Test
  void test_01_02() {
    assertThat(SeleniumConfig.getProperty("noKey", "dummy")).isEqualTo("dummy");
  }

  @Test
  void test_02() {
    assertThat(SeleniumConfig.getBaseUrl()).isEqualTo("http://localhost:8080");
  }

  @Test
  void test_03() {
    assertThat(SeleniumConfig.getUsername()).isEqualTo("user");
  }

  @Test
  void test_04() {
    assertThat(SeleniumConfig.getPassword()).isEqualTo("pass");
  }

  @Test
  void test_05() {
    assertThat(SeleniumConfig.getCompany()).isEqualTo("ABCDE00001");
  }
}
