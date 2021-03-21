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

  @DisplayName("あまり")
  @Test
  void test_10() {

    BigDecimal three = BigDecimal.valueOf(3);
    BigDecimal ten = BigDecimal.valueOf(10.11);
    BigDecimal remainder = ten.remainder(three);
    assertThat(
        remainder
    ).isEqualTo(BigDecimal.valueOf(1.11));
  }

  @DisplayName("new 時にDoubleわたすと事故る")
  @Test
  void test_11() {

    assertThat(
        new BigDecimal(123.45).toString()
    ).isEqualTo("123.4500000000000028421709430404007434844970703125");

    assertThat(
        new BigDecimal("123.45").toString()
    ).isEqualTo("123.45");
    assertThat(
        BigDecimal.valueOf(123.45).toString()
    ).isEqualTo("123.45");
  }
}
