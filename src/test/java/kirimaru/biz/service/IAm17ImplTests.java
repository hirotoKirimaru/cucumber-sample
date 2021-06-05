package kirimaru.biz.service;

import kirimaru.biz.domain.CustomYearMonth;
import kirimaru.biz.domain.date.SystemDateTimeResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IAm17ImplTests {
  @Mock
  SystemDateTimeResolver systemDateTimeResolver;
  @InjectMocks
  IAm17Impl target;

  @Test
  void test_01() {
    when(systemDateTimeResolver.now()).thenReturn(LocalDateTime.of(2021, 6, 5, 0, 0));

    assertThat(target.iam17(LocalDate.of(1992, 2, 4)))
        .isEqualTo(CustomYearMonth.of(17, 148));
  }

  @Test
  void test_02() {
    when(systemDateTimeResolver.now()).thenReturn(LocalDateTime.of(2021, 6, 5, 0, 0));

    assertThat(target.iam17(LocalDate.of(1992, 3, 4)))
        .isEqualTo(CustomYearMonth.of(17, 147));
  }
}
