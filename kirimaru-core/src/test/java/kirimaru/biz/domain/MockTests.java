package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MockTests {

  public class Target {

    public String getFirst() {
      return "1";
    }

    // firstに依存しているstaticメソッド
    public String getSecond() {
      return getFirst();
    }

    public String getThird() {
      return getSecond();
    }
  }

  @Nested
  class NoMock {

    Target target = new Target();

    @Test
    void test_01() {
      assertThat(
          target.getFirst()
      ).isEqualTo("1");
    }

    @Test
    void test_02() {
      assertThat(
          target.getSecond()
      ).isEqualTo("1");
    }
  }

  @Nested
  class Mock {

    @Test
    void test_01() {
      Target target = Mockito.mock(Target.class, Mockito.CALLS_REAL_METHODS);
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(target.getFirst()).isEqualTo("1");
      softly.assertThat(target.getSecond()).isEqualTo("1");

      when(target.getFirst()).thenReturn("123");

      softly.assertThat(target.getFirst()).isEqualTo("123");
      softly.assertThat(target.getSecond()).isEqualTo("123");

      softly.assertAll();
    }

    @Test
    void test_02() {
      Target target = Mockito.mock(Target.class);
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(target.getFirst()).isNull();
      softly.assertThat(target.getSecond()).isNull();

      when(target.getFirst()).thenReturn("123");

      softly.assertThat(target.getFirst()).isEqualTo("123");
      softly.assertThat(target.getSecond()).isNull();

      softly.assertAll();
    }
  }
}
