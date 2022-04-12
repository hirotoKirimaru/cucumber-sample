package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class IllegalDateTests {

  public record RequestDate(String value) {

    private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static RequestDate of(@NonNull String value) {
      String formatted = LocalDate.parse(value, DATE_FORMATTER).format(DATE_FORMATTER);
      if (!value.equals(formatted)) {
        throw new RuntimeException(value);
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
}