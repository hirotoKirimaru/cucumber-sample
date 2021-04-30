package kirimaru.biz.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ContractTest {

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class CanExpire {
    @MethodSource(value = "canExpire")
    @ParameterizedTest(name = "契約日が{0}, 解約日が{1}の時、{2}")
    void test_01(LocalDate start, LocalDate end, boolean result) {
      var target = Contract.builder()
          .contractDate(start)
          .expireDate(end)
          .build();

      assertThat(target.canExpire2()).isEqualTo(result);
    }

    private Stream<Arguments> canExpire() {
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
