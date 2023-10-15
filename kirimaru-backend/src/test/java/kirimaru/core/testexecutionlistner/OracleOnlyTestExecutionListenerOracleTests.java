package kirimaru.core.testexecutionlistner;

import static org.junit.jupiter.api.Assertions.assertTrue;

import kirimaru.core.anotation.OracleOnly;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

@TestExecutionListeners(OracleOnlyTestExecutionListener.class)
@SpringBootTest(properties = {
    "spring.dummy-datasource.driver-class-name:oracle.jdbc.OracleDriver",})
public class OracleOnlyTestExecutionListenerOracleTests {

  @Test
  void test_01() {
    assertTrue(true, "実行すること");
  }

  @OracleOnly
  @Test
  void test_02() {
    assertTrue(true, "実行すること");
  }

  @OracleOnly
  @Nested
  class Nest {

    @Test
    void test_01() {
      assertTrue(true, "実行すること");
    }
  }
}
