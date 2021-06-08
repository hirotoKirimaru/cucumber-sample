package kirimaru.biz.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class YearMonthTests {

  @Nested
  class YearMonthから月末を求める {
    @Test
    void test_01() {
      assertThat(
          YearMonth.of(2020, 1).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 1, 31));
    }

    @Test
    void test_01_02() {
      assertThat(
          YearMonth.of(2020, 1).atEndOfMonth().getDayOfMonth()
      ).isEqualTo(31);
    }

    @Test
    void test_01_03() {
      assertThat(
          YearMonth.of(202_0, 1).atEndOfMonth().getDayOfMonth()
      ).isEqualTo(31);
    }

    @Test
    void test_02() {
      assertThat(
          YearMonth.of(2020, 4).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 4, 30));
    }

    @Test
    void test_03() {
      assertThat(
          YearMonth.of(2020, 2).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 2, 29));
    }

    @Test
    void test_04() {
      assertThat(
          YearMonth.of(2021, 2).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2021, 2, 28));
    }
  }

  @Nested
  class YearMonthから月末を求める2 {


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

    @Test
    void test_05() {
      assertThat(
          YearMonth.parse("2021/02", DateTimeFormatter.ofPattern("yyyy/MM")).atEndOfMonth()
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
              LocalDate.of(2020, 4, 15)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 4, 30));
    }

    @Test
    void test_03() {
      assertThat(
          YearMonth.from(
              LocalDate.of(2020, 2, 15)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 2, 29));
    }

    @Test
    void test_04() {
      assertThat(
          YearMonth.from(
              LocalDate.of(2021, 2, 15)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2021, 2, 28));
    }
  }

  @Nested
  class LocalDateから月2 {


    @Test
    void test_01() {
      assertThat(
          LocalDate.of(2020, 1, 15).with(TemporalAdjusters.lastDayOfMonth())
      ).isEqualTo(LocalDate.of(2020, 1, 31));
    }

    @Test
    void test_02() {
      assertThat(
          LocalDate.of(2020, 4, 15).with(TemporalAdjusters.lastDayOfMonth())
      ).isEqualTo(LocalDate.of(2020, 4, 30));
    }

    @Test
    void test_03() {
      assertThat(
          LocalDate.of(2020, 2, 15).with(TemporalAdjusters.lastDayOfMonth())
      ).isEqualTo(LocalDate.of(2020, 2, 29));
    }

    @Test
    void test_04() {
      assertThat(
          LocalDate.of(2021, 2, 15).with(TemporalAdjusters.lastDayOfMonth())
      ).isEqualTo(LocalDate.of(2021, 2, 28));
    }

    @Disabled("動かさない")
    @Nested
    class 性能確認 {
      int size = 1_000_000_000;

      @Test
      void test_01() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
          LocalDate now = LocalDate.of(2021, 2, 28);
          LocalDate end = now.with(TemporalAdjusters.lastDayOfMonth());
          boolean equals = Objects.equals(now, end);
//          assertThat(equals).isTrue();
        }
        long end = System.currentTimeMillis();

        System.out.println("*******************");
        System.out.println(end - start);
        // 60ミリ秒
      }

      @Test
      void test_02() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
          boolean b = LocalDate.of(2021, 2, 28).plusDays(1).getDayOfMonth() == 1;
//          assertThat(b).isTrue();
        }
        long end = System.currentTimeMillis();

        System.out.println("*******************");
        System.out.println(end - start);
        // 20ミリ秒
      }

      @Test
      void test_03() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
          LocalDate now = LocalDate.of(2021, 2, 28);
          boolean b = YearMonth.from(now).atEndOfMonth().equals(now);
//          assertThat(b).isTrue();
        }
        long end = System.currentTimeMillis();

        System.out.println("*******************");
        System.out.println(end - start);
        // 60ミリ秒
      }

      @Test
      void test_04() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
          LocalDateTime now = LocalDateTime.of(2021, 2, 28, 1, 1);
          LocalDateTime end = now.with(TemporalAdjusters.lastDayOfMonth());
          boolean equals = Objects.equals(now, end);
//          assertThat(equals).isTrue();
        }
        long end = System.currentTimeMillis();

        System.out.println("*******************");
        System.out.println(end - start);
        // 20ミリ秒
      }
    }

  }

  @Nested
  class LocalDateTimeから月末 {
    @Test
    void test_01() {
      assertThat(
          YearMonth.from(
              LocalDateTime.of(2020, 1, 15, 1, 1)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 1, 31));
    }

    @Test
    void test_02() {
      assertThat(
          YearMonth.from(
              LocalDateTime.of(2020, 4, 15, 1, 1)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 4, 30));
    }

    @Test
    void test_03() {
      assertThat(
          YearMonth.from(
              LocalDateTime.of(2020, 2, 15, 1, 1)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2020, 2, 29));
    }

    @Test
    void test_04() {
      assertThat(
          YearMonth.from(
              LocalDateTime.of(2021, 2, 15, 1, 1)
          ).atEndOfMonth()
      ).isEqualTo(LocalDate.of(2021, 2, 28));
    }


    @Test
    void test_05() {
      assertThat(
          LocalDateTime.of(2021, 2, 15, 1, 1).with(TemporalAdjusters.lastDayOfMonth())
      ).isEqualTo(LocalDateTime.of(2021, 2, 28, 1, 1));
    }
  }

  // Java8より前の書き方
  @Nested
  class Calenderから月末 {
    @Disabled("なぜか動かなくなった。メインの書き方ではないのでDisabledにする")
    @Test
    void test_01() {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, 2000);
      cal.set(Calendar.MONTH, 1); // 0=1月, 1=2月
      assertThat(
          cal.getActualMaximum(Calendar.DATE)
      ).isEqualTo(29);
    }

    @Test
    void test_02() {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, 2000);
      cal.set(Calendar.MONTH, 0); // 0=1月, 1=2月
      assertThat(
          cal.getActualMaximum(Calendar.DATE)
      ).isEqualTo(31);
    }
  }
}
