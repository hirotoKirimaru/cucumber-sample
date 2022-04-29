package kirimaru.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TermTest {

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class CanExpire {

    @MethodSource(value = "canExpire")
    @ParameterizedTest(name = "契約日が{0}, 解約日が{1}の時、{2}")
//    @ParameterizedTest(name="[{index}]{displayName}{arguments}{argumentsWithNames}")
//    @ParameterizedTest
    void test_01(LocalDate start, LocalDate end, boolean result) {
      var target = Term.builder()
          .start(start)
          .end(end)
          .build();

      assertThat(target.canExpire()).isEqualTo(result);
    }

    private Stream<Arguments> canExpire() {
      return Stream.of(
          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 3, 31), true),
          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 3, 30), false),
          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 2, 28), true),
          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2024, 2, 29), true),
          Arguments.of(LocalDate.of(2020, 12, 10), LocalDate.of(2021, 12, 9), true),
          Arguments.of(LocalDate.of(2020, 12, 10), LocalDate.of(2021, 12, 10), false),
          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2024, 11, 30), false),
          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2024, 12, 31), false),
          Arguments.of(LocalDate.of(2020, 11, 30), LocalDate.of(2021, 3, 31), false),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 4, 30), true),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 3, 31), false),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 2, 28), true),
          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2024, 2, 29), true),
          Arguments.of(LocalDate.of(2020, 12, 30), LocalDate.of(2024, 2, 28), false),
          Arguments.of(LocalDate.of(2020, 12, 30), LocalDate.of(2024, 2, 29), true)
      );
    }
  }

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class CanExpire_Another {

    @BeforeEach
    void setUp() {
      System.out.println("setup");
    }

    @BeforeAll
    void beforeAll() {
      System.out.println("beforeAll");
    }

    @AfterAll
    void afterAll() {
      System.out.println("afterAll");
    }

    @AfterEach
    void tearDown() {
      System.out.println("afterEach");
    }

    @MethodSource(value = "canExpire")
    @ParameterizedTest
    void test_01(Param param) {
      var target = Term.builder()
          .start(param.start)
          .end(param.end)
          .build();

      assertThat(target.canExpire()).isEqualTo(param.result);
    }

    //    @Builder
    @Data
    @AllArgsConstructor
    class Param implements Arguments {

      private LocalDate start;
      private LocalDate end;
      private boolean result;

      @Override
      public Object[] get() {
        return new Object[0];
      }
    }

    private Stream<Arguments> canExpire() {
      return Stream.of(
//          Arguments.of(Param.builder().start(LocalDate.of(2020, 12, 1)).end(LocalDate.of(2021, 3, 31)).result(true).build())
          Arguments.of(new Param(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 3, 31), true))
//          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 3, 30), false),
//          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2021, 2, 28), true),
//          Arguments.of(LocalDate.of(2020, 12, 1), LocalDate.of(2024, 2, 29), true),
//          Arguments.of(LocalDate.of(2020, 12, 10), LocalDate.of(2021, 12, 9), true),
//          Arguments.of(LocalDate.of(2020, 12, 10), LocalDate.of(2021, 12, 10), false),
//          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2024, 11, 30), false),
//          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2024, 12, 31), false),
//          Arguments.of(LocalDate.of(2020, 11, 30), LocalDate.of(2021, 3, 31), false),
//          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 4, 30), true),
//          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 3, 31), false),
//          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2021, 2, 28), true),
//          Arguments.of(LocalDate.of(2020, 12, 31), LocalDate.of(2024, 2, 29), true),
//          Arguments.of(LocalDate.of(2020, 12, 30), LocalDate.of(2024, 2, 28), false),
//          Arguments.of(LocalDate.of(2020, 12, 30), LocalDate.of(2024, 2, 29), true)
      );
    }
  }

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class CanExpire2 {

    @MethodSource(value = "canExpire")
    @ParameterizedTest(name = "契約日が{0}, 解約日が{1}の時、{2}")
    void test_01(LocalDate start, LocalDate end, boolean result) {
      var target = Term.builder()
          .start(start)
          .end(end)
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


  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class computeBetweenMonthsRoundUp {

    @MethodSource(value = "param")
    @ParameterizedTest(name = "開始日が{0}, 終了日が{1}の時、経過月は{2}")
//    @ParameterizedTest(name = "|{0}|{1}|{2}|")
    void test_01(LocalDate start, LocalDate end, int result) {
      var target = Term.builder()
          .start(start)
          .end(end)
          .build();

      assertThat(target.computeBetweenMonthsRoundUp()).isEqualTo(result);
    }

    private Stream<Arguments> param() {
      return Stream.of(
          Arguments.of(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 15), 1),
          Arguments.of(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 31), 1),
          Arguments.of(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 2, 1), 2),
          Arguments.of(LocalDate.of(2020, 1, 15), LocalDate.of(2020, 2, 10), 1),
          Arguments.of(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 12, 31), 12),
          Arguments.of(LocalDate.of(2020, 1, 1), LocalDate.of(2021, 12, 31), 24),
          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2021, 2, 28), 12), // うるう
          Arguments.of(LocalDate.of(2019, 2, 28), LocalDate.of(2020, 2, 29), 13), // うるう
          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2024, 2, 28), 48), //うるう
          Arguments.of(LocalDate.of(2020, 2, 29), LocalDate.of(2024, 2, 29), 49), //うるう
          Arguments.of(LocalDate.of(2020, 2, 1), LocalDate.of(2020, 2, 28), 1),
          Arguments.of(LocalDate.of(2020, 2, 1), LocalDate.of(2020, 2, 29), 1)
//          Arguments.of(LocalDate.of(2020, 1, 1), LocalDate.of(2021, 2, 1), 2),
      );
    }
  }


  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class IsIllegalSituation {

    @MethodSource(value = "param")
    @ParameterizedTest(name = "開始が{0}, 終了が{1}の時、前後関係が異常か{2}")
    void test_01(LocalDate start, LocalDate end, boolean result) {
      var target = Term.builder()
          .start(start)
          .end(end)
          .build();

      assertThat(target.isIllegalSituation()).isEqualTo(result);
    }

    private Stream<Arguments> param() {
      LocalDate base = LocalDate.of(2020, 1, 15);
      return Stream.of(
          Arguments.of(base, base.plusDays(1), false),
          Arguments.of(base, base, false),
          Arguments.of(base.plusDays(1), base, true)
      );
    }
  }


}
