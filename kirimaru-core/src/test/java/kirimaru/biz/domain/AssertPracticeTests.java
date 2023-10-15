package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class AssertPracticeTests {
  @Test
  void test_01() {
    assertThatThrownBy(
        () -> AssertPractice.notNullValidate(null)
    ).isInstanceOfSatisfying(
        IllegalStateException.class,
        (e) -> assertThat(e.getMessage()).isEqualTo("param is null")
    );
  }

  @Test
  void test_02() {
    AssertPractice.notNullValidate("");
  }
}
