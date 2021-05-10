package kirimaru.biz.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;

public class YearMonthTests {
  @Test
  void test_01() {
    assertThat(
        YearMonth.parse("2020-01").atEndOfMonth()
    ).isEqualTo(LocalDate.of(2020, 1, 31));
  }
}
