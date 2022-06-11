package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StaticMockTests {

  public class Target {

    public static String getFirst() {
      return "1";
    }

    // firstに依存しているstaticメソッド
    public static String getSecond() {
      return getFirst();
    }

    public static String getThird() {
      return getSecond();
    }
  }

  @Nested
  class NoMock {

    @Test
    void test_01() {
      assertThat(
          Target.getFirst()
      ).isEqualTo("1");
    }

    @Test
    void test_02() {
      assertThat(
          Target.getSecond()
      ).isEqualTo("1");
    }
  }

  @Nested
  class Mock {

    @Test
    void test_01() {
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(Target.getFirst()).isEqualTo("1");
      softly.assertThat(Target.getSecond()).isEqualTo("1");

      try (var mocked = Mockito.mockStatic(Target.class)) {
        mocked.when(() -> Target.getFirst()).thenReturn("123");

        softly.assertThat(Target.getFirst()).isEqualTo("123");
        softly.assertThat(Target.getSecond()).isEqualTo(null); // nullになってしまう
      }

      softly.assertAll();
    }

    @Test
    void test_02() {
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(Target.getFirst()).isEqualTo("1");
      softly.assertThat(Target.getSecond()).isEqualTo("1");

      try (var mocked = Mockito.mockStatic(Target.class, Mockito.CALLS_REAL_METHODS)) {
        //
//        mocked.when(() -> Target.getFirst()).thenReturn("123");
        // こっちでも可能
        when(Target.getFirst()).thenReturn("123");

        softly.assertThat(Target.getFirst()).isEqualTo("123");
        softly.assertThat(Target.getSecond()).isEqualTo("123");
        softly.assertThat(Target.getThird()).isEqualTo("123");
      }
      softly.assertThat(Target.getFirst()).isEqualTo("1");
      softly.assertThat(Target.getSecond()).isEqualTo("1");

      softly.assertAll();
    }
  }
}
