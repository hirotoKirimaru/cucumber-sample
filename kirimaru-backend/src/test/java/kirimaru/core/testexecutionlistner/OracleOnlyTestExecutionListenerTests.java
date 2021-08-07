package kirimaru.core.testexecutionlistner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import kirimaru.core.anotation.OracleOnly;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@TestExecutionListeners(OracleOnlyTestExecutionListener.class)
@SpringJUnitConfig
public class OracleOnlyTestExecutionListenerTests {

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
