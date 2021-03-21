package jp.co.kelly.biz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BigDecimalTests {
  @Test
  void test_01() {

    assertThat(
        BigDecimal.valueOf(2)
            .divide(BigDecimal.valueOf(3), 10, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100))
            .setScale(2, RoundingMode.HALF_UP)
    ).isEqualTo(BigDecimal.valueOf(66.67));
  }

  @DisplayName("0と0.00では=にならない。")
  @Test
  void test_02() {
    assertThat(
        BigDecimal.ZERO.equals(BigDecimal.ZERO.setScale(2))
    ).isEqualTo(false);
  }

  @Test
  void test_03() {
    BigDecimal one = BigDecimal.ZERO;
    BigDecimal two = BigDecimal.ZERO.setScale(2);

    assertThat(
        one.signum() == two.signum()
    ).isEqualTo(true);
  }

  @Test
  void test_04() {
    List<BigDecimal> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(BigDecimal.ONE);
    }

    assertThat(
        list.stream().reduce(
            BigDecimal.ZERO, BigDecimal::add
        )
    ).isEqualTo(BigDecimal.TEN);
  }


  @Test
  void test_05() {

    BigDecimal one = BigDecimal.ONE.setScale(1);
    BigDecimal two = BigDecimal.ONE.setScale(2);
    assertThat(
        one.equals(two)
    ).isEqualTo(false);
  }

  @Test
  void test_06() {

    BigDecimal one = BigDecimal.ONE.setScale(1);
    BigDecimal two = BigDecimal.ONE.setScale(2);
    BigDecimal subtract = one.subtract(two);
    assertThat(
        subtract.signum() == 0
    ).isEqualTo(true);
  }

  @Test
  void test_07() {

    BigDecimal one = BigDecimal.ONE.setScale(100);
    BigDecimal two = BigDecimal.valueOf(1.01);
    BigDecimal subtract = one.subtract(two);
    assertThat(
        subtract.signum() != 0
    ).isEqualTo(true);
  }

  @DisplayName("精度は大きい方になる")
  @Test
  void test_08() {

    BigDecimal one = BigDecimal.ONE.setScale(10);
    BigDecimal two = BigDecimal.ONE.setScale(2);
    BigDecimal subtract = one.subtract(two);

    assertThat(
        subtract.scale()
    ).isEqualTo(10);

    BigDecimal subtract2 = two.subtract(one);

    assertThat(
        subtract.scale()
    ).isEqualTo(10);
  }

  @Test
  void test_09() {

    BigDecimal one = BigDecimal.ONE.setScale(1);
    BigDecimal two = BigDecimal.ONE.setScale(2);
    assertThat(
        one.compareTo(two) == 0
    ).isEqualTo(true);
  }
}