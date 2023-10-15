package kirimaru.core.testexecutionlistner;

import static org.junit.Assert.fail;

import kirimaru.core.anotation.OracleOnly;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;

@TestExecutionListeners(OracleOnlyTestExecutionListener.class)
@SpringBootTest(properties = {
    "spring.dummy-datasource.driver-class-name:org.postgresql.Driver",})
public class OracleOnlyTestExecutionListenerPostgresTests {

  @OracleOnly
  @Test
  void test_01() {
    fail();
  }

  @OracleOnly
  @Nested
  class Nest {

    @Test
    void test_01() {
      fail();
    }
  }
}
