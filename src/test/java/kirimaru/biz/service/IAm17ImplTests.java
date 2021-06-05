package kirimaru.biz.service;

import kirimaru.biz.domain.CustomYearMonth;
import kirimaru.biz.domain.date.SystemDateTimeResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IAm17ImplTests {
  @Mock
  SystemDateTimeResolver systemDateTimeResolver;
  @InjectMocks
  IAm17Impl target;

  @BeforeEach
  void setUp() {
    when(systemDateTimeResolver.now())
        .thenReturn(LocalDateTime.of(2021, 6, 5, 0, 0));
  }

  @ParameterizedTest
  @MethodSource(value = "param")
  @DisplayName("2021/06/05が現在時刻")
  void test_01(LocalDate birth, int year, int month) {
    assertThat(target.iam17(birth))
        .isEqualTo(CustomYearMonth.of(year, month));
  }

  public static Stream<Arguments> param() {
    return Stream.of(
        Arguments.of(LocalDate.of(1992, 2, 4), 17, 148),
        Arguments.of(LocalDate.of(1992, 3, 4), 17, 147),
        Arguments.of(LocalDate.of(2021, 6, 4), 17, -204),
        Arguments.of(LocalDate.of(2021, 6, 5), 17, -204),
        Arguments.of(LocalDate.of(2021, 6, 6), 17, -205),
        Arguments.of(LocalDate.of(2021, 5, 4), 17, -203),
        Arguments.of(LocalDate.of(2021, 5, 5), 17, -203),
        Arguments.of(LocalDate.of(2021, 5, 6), 17, -204)
    )
        ;

  }
}
