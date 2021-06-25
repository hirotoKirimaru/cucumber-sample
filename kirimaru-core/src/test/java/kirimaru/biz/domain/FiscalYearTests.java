package kirimaru.biz.domain;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FiscalYearTests {
  @Test
  void test_01() {
    assertThatThrownBy(
        () -> new FiscalYear("123")
    ).isInstanceOf(RuntimeException.class);
  }

  @Test
  void test_02() {
    new FiscalYear("1234");
  }

  @Test
  void test_03() {
    assertThat(
        new FiscalYear("2021").toShortYear()
    ).isEqualTo("21");
  }

  @Test
  void test_04() {
    assertThat(
        new FiscalYear("2021").toYear()
    ).isEqualTo(Year.of(2021));
  }
}
