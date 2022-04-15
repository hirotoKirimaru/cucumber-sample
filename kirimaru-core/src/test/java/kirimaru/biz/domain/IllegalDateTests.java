package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 異常日付のチェックを行う
 * 異常日付 = 20220229等の異常うるう日も含む
 */
class IllegalDateTests {

  public record RequestDate(String value) {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final static DateTimeFormatter DATE_FORMATTER_2 =
        // NOTE: 厳密モードにすることで異常日付を丸めないようにする
        DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(ResolverStyle.STRICT);

    public static RequestDate of(@NonNull String value) {
      String formatted = LocalDate.parse(value, DATE_FORMATTER).format(DATE_FORMATTER);
      if (!value.equals(formatted)) {
        throw new RuntimeException(value);
      }

      return new RequestDate(value);
    }

    public static RequestDate of2(@NonNull String value) {
      try {
        LocalDate.parse(value, DATE_FORMATTER_2);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        throw new RuntimeException(e);
      }

      return new RequestDate(value);
    }
  }

  @Nested
  class Of {

    @ValueSource(strings = {
        "20200229",
        "20220228",
    })
    @ParameterizedTest
    void test1(String value) {
      RequestDate.of(value);
    }

    @ValueSource(strings = {
        "20200230",
        "20220229",
        "20220230",
        "20220231"
    })
    @ParameterizedTest
    void illegal(String value) {
      assertThatThrownBy(
          () -> RequestDate.of(value)
      ).isInstanceOfSatisfying(RuntimeException.class,
          (e) -> assertThat(e.getMessage()).isEqualTo(value));
    }
  }

  @Nested
  class Of2 {

    @ValueSource(strings = {
        "20200229",
        "20220228",
    })
    @ParameterizedTest
    void test1(String value) {
      RequestDate.of2(value);
    }

    @ValueSource(strings = {
        "20200230",
        "20220229",
        "20220230",
        "20220231"
    })
    @ParameterizedTest
    void illegal(String value) {
      assertThatThrownBy(
          () -> RequestDate.of2(value)
      ).isInstanceOf(RuntimeException.class);
    }

    @ValueSource(strings = {
        "令和2年2月4日",
        "R20204",
        "2022-02-04",
        "2022/02/04"
    })
    @ParameterizedTest
    void illegal_format(String value) {
      assertThatThrownBy(
          () -> RequestDate.of2(value)
      ).isInstanceOf(RuntimeException.class);
    }
  }


  @EnumSource(ResolverStyle.class)
  @ParameterizedTest
  void test_01(ResolverStyle value) {
    try {
      LocalDate uuuuMMdd = LocalDate.parse("20220229",
          DateTimeFormatter.ofPattern("uuuuMMdd").withResolverStyle(value));
      System.out.println(uuuuMMdd);
    } catch( Exception e){
      System.out.println(e);
    }
  }
}