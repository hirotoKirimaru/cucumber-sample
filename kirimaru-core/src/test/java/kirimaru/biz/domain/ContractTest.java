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

class ContractTest {

  @Nested
  @TestInstance(TestInstance.Lifecycle.PER_CLASS)
  class CanExpire {
    @MethodSource(value = "canExpire")
    @ParameterizedTest(name = "契約日が{0}, 解約日が{1}の時、{2}")
//    @ParameterizedTest(name="[{index}]{displayName}{arguments}{argumentsWithNames}")
//    @ParameterizedTest
    void test_01(LocalDate start, LocalDate end, boolean result) {
      var target = Contract.builder()
          .contractDate(start)
          .expireDate(end)
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
      var target = Contract.builder()
          .contractDate(param.start)
          .expireDate(param.end)
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
