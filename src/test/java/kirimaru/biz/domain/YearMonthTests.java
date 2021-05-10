package kirimaru.biz.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;

public class YearMonthTests {

  @Nested
  class YearMonthから月末を求める {


    @Test
    void test_01() {
      assertThat(
          YearMonth.parse("2020-01").atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 1, 31));
    }

    @Test
    void test_02() {
      assertThat(
          YearMonth.parse("2020-04").atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 4, 30));
    }

    @Test
    void test_03() {
      assertThat(
          YearMonth.parse("2020-02").atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 2, 29));
    }

    @Test
    void test_04() {
      assertThat(
          YearMonth.parse("2021-02").atEndOfMonth()
      ).isEqualTo(LocalDate.of(2021, 2, 28));
    }
  }

  @Nested
  class LocalDateから月末 {


    @Test
    void test_01() {
      assertThat(
          YearMonth.from(
              LocalDate.of(2020, 1, 15)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 1, 31));
    }

    @Test
    void test_02() {
      assertThat(
          YearMonth.from(
              LocalDate.of(2020,4, 15)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 4, 30));
    }

    @Test
    void test_03() {
      assertThat(
          YearMonth.from(
              LocalDate.of(2020,2, 15)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 2, 29));
    }

    @Test
    void test_04() {
      assertThat(
          YearMonth.from(
              LocalDate.of(2021,2, 15)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2021, 2, 28));
    }

  }
}
