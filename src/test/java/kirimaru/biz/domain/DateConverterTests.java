package kirimaru.biz.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DateConverterTests {
  DateConverter dateConverter = new DateConverter();

  @Nested
  class ToString {
    @Test
    void test_01() {
      LocalDateTime now = LocalDateTime.of(2020, 12, 1, 2, 3, 4);

      assertThat(
        dateConverter.toString(now)
      ).isEqualTo("20201201");

    }
  }
}
