import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class AbstractConfigTests {
  @Test
  void test_01() {

    assertThat(AbstractConfig.getProperty("baseUrl")).isEqualTo("http://localhost:8080");
  }
}
