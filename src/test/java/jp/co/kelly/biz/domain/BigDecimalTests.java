package jp.co.kelly.biz.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class BigDecimalTests {
  @Test
  void test_01() {

    assertThat(
        BigDecimal.valueOf(2)
            .setScale(10, RoundingMode.HALF_UP)
            .divide(BigDecimal.valueOf(3), RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100))
            .setScale(2, RoundingMode.HALF_UP)
    ).isEqualTo(BigDecimal.valueOf(66.67));


  }
}
