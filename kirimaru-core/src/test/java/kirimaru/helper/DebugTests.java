package kirimaru.helper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DebugTests {
  public class TestClass implements Debug {
  }

  @Nested
  class LineNo {
    @Test
    void test_01() {
      assertThat(
          new TestClass().getLineNo()
      ).isEqualTo(17);
    }

    @Test
    void test_02() {
      assertThat(
          Debugs.getLineNo()
      ).isEqualTo(24);
    }
  }
}