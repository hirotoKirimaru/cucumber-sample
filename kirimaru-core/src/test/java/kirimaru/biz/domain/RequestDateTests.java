package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.LocalDate;
import java.util.stream.Stream;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RequestDateTests {

  @Test
  void test_01() {
    LocalDate localDate = LocalDate.of(2022, 5, 7);
    String string = "20220507";
    assertThat(
        RequestDate.of(string)
    ).isEqualTo(new RequestDate(string, localDate));
  }

  @Test
  void test_02() {
    LocalDate localDate = LocalDate.of(2022, 5, 7);
    String string = "20220507";
    RequestDate requestDate = new RequestDate(string, localDate);

    SoftAssertions softly = new SoftAssertions();

    softly.assertThat(requestDate.string()).isEqualTo(string);
    softly.assertThat(requestDate.localDate()).isEqualTo(localDate);

    softly.assertAll();
  }

  @Test
  void test_03() {
    LocalDate localDate = LocalDate.of(2022, 5, 7);
    String string = "20220507";
    RequestDate requestDate = new RequestDate(string, localDate);
    String string2 = "20200101";
    LocalDate localDate2 = LocalDate.of(2020, 1, 1);
    requestDate.string(string2).localDate(localDate2);

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(requestDate.string()).isEqualTo(string2);
    softly.assertThat(requestDate.localDate()).isEqualTo(localDate2);

    softly.assertAll();
  }

  @Nested
  @TestInstance(Lifecycle.PER_CLASS)
  class to日割りなし日付 {

    @MethodSource(value = "value")
    @ParameterizedTest(name = "契約日が{0}, 解約日が{1}の時、{2}")
    void test_01(LocalDate start, LocalDate end, boolean result) {
      // テストデータを共有する意図で入れてみる
      assumeTrue(result);

      // THEN
      assertThat(RequestDate.of(start).from年月to日割りなし日付(end)).isEqualTo(end);
    }

    private Stream<Arguments> value() {
      return Stream.of(
          Arguments.of(LocalDate.of(2020, 12, 10), LocalDate.of(2021, 2, 9), true),
          Arguments.of(LocalDate.of(2020, 12, 10), LocalDate.of(2021, 2, 1), false),
          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 3, 31), true),
          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 3, 30), false),
          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 2, 28), true),
          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2024, 2, 29), true),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 3, 30), true),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 4, 30), true),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 3, 31), false),
          Arguments.of(LocalDate.of(2020, 11, 30), LocalDate.of(2021, 3, 31), false),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 2, 28), true),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2024, 2, 29), true),
          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2024, 12, 31), false),
          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2024, 11, 30), false)
      );
    }
  }
}