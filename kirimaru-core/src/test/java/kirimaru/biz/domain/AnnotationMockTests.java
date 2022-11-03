package kirimaru.biz.domain;

import static org.mockito.Mockito.when;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(MockitoExtension.class)
class AnnotationMockTests {

  @Component
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
  class RealMethod {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    Target target;

    @Test
    void test_01() {
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(target.getFirst()).isEqualTo("1");
      softly.assertThat(target.getSecond()).isEqualTo("1");

      when(target.getFirst()).thenReturn("123");
      softly.assertThat(target.getFirst()).isEqualTo("123");
      softly.assertThat(target.getSecond()).isEqualTo("123");

      softly.assertAll();
    }

    @Disabled("第二引数の変更が必要")
    @Test
    void test_02() {
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(target.getFirst()).isNull();
      softly.assertThat(target.getSecond()).isNull();

      when(target.getFirst()).thenReturn("123");

      softly.assertThat(target.getFirst()).isEqualTo("123");
      softly.assertThat(target.getSecond()).isNull();

      softly.assertAll();
    }
  }

  @Nested
  @SpringJUnitConfig(AnnotationMockTests.Config.class)
  class MockBean {

    @org.springframework.boot.test.mock.mockito.MockBean(answer = Answers.CALLS_REAL_METHODS)
    Target target;

    @Test
    void test_01() {
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(target.getFirst()).isEqualTo("1");
      softly.assertThat(target.getSecond()).isEqualTo("1");

      when(target.getFirst()).thenReturn("123");
      softly.assertThat(target.getFirst()).isEqualTo("123");
      softly.assertThat(target.getSecond()).isEqualTo("123");

      softly.assertAll();
    }

    @Disabled("第二引数の変更が必要")
    @Test
    void test_02() {
      SoftAssertions softly = new SoftAssertions();
      softly.assertThat(target.getFirst()).isNull();
      softly.assertThat(target.getSecond()).isNull();

      when(target.getFirst()).thenReturn("123");

      softly.assertThat(target.getFirst()).isEqualTo("123");
      softly.assertThat(target.getSecond()).isNull();

      softly.assertAll();
    }
  }

  @ComponentScan({"kirimaru.biz.domain.hogehoge.tekitou"})
  public static class Config {

  }
}