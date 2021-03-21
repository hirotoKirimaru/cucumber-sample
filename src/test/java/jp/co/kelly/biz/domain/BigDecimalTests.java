package jp.co.kelly.biz.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
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
        new BigDecimal(123.45).toPlainString()
    ).isEqualTo("123.4500000000000028421709430404007434844970703125");

    assertThat(
        new BigDecimal("123.45").toPlainString()
    ).isEqualTo("123.45");
    assertThat(
        BigDecimal.valueOf(123.45).toPlainString()
    ).isEqualTo("123.45");
  }

  @DisplayName("new 時にDoubleわたすと事故る")
  @Test
  void test_12() {

    assertThat(
        BigDecimal.valueOf(0.0000001).toString()
    ).isEqualTo("1.0E-7");
    assertThat(
        BigDecimal.valueOf(0.0000001).toPlainString()
    ).isEqualTo("0.00000010");
  }

  @Test
  void test_13() {

    assertThat(
        BigDecimal.ONE.negate().toPlainString()
    ).isEqualTo("-1");

    assertThat(
        BigDecimal.ONE.negate().negate().toPlainString()
    ).isEqualTo("1");
  }

  @Test
  void test_14() {

    assertThat(
        BigDecimal.ONE.toPlainString()
    ).isEqualTo("1");

    assertThat(
        BigDecimal.ONE.negate().plus().toPlainString()
    ).isEqualTo("-1");
  }

  @Test
  void test_15() {

    assertThat(
        BigDecimal.ONE.scaleByPowerOfTen(1).toPlainString()
    ).isEqualTo("10");

    // setScaleしないと1E+1になっちゃう…。
    assertThat(
        BigDecimal.ONE.setScale(1).scaleByPowerOfTen(1)
    ).isEqualTo(BigDecimal.TEN);

    assertThat(
        BigDecimal.TEN.scaleByPowerOfTen(-1)
    ).isEqualTo(BigDecimal.ONE.setScale(1));
  }

  @DisplayName("性能確認")
  @Test
  void test_16() {
    log.info("***************");
    log.info("compareTo");
    for (int i = 0; i < 1000000; i++) {
      boolean b = BigDecimal.ZERO.compareTo(BigDecimal.ZERO) == 0;
    }
    log.info("***************");

    log.info("***************");
    log.info("signum");
    for (int i = 0; i < 1000000; i++) {
      boolean b = BigDecimal.ZERO.signum() == 0;
    }
    log.info("***************");


    // compareTo内部でsignumを呼んでるから、0除算回避であればsignumを使うべき。
    // Quick path for equal scale and non-inflated case.
//    if (scale == val.scale) {
//      long xs = intCompact;
//      long ys = val.intCompact;
//      if (xs != INFLATED && ys != INFLATED)
//        return xs != ys ? ((xs > ys) ? 1 : -1) : 0;
//    }
//    int xsign = this.signum();
//    int ysign = val.signum();
//    if (xsign != ysign)
//      return (xsign > ysign) ? 1 : -1;
//    if (xsign == 0)
//      return 0;
//    int cmp = compareMagnitude(val);
//    return (xsign > 0) ? cmp : -cmp;
  }

  @Test
  void test_17() {

    assertThat(
        BigDecimal.ONE.movePointRight(1)
    ).isEqualTo(BigDecimal.TEN);

    assertThat(
        BigDecimal.TEN.movePointLeft(1)
    ).isEqualTo(BigDecimal.ONE.setScale(1));

    assertThat(
        BigDecimal.ONE.multiply(BigDecimal.TEN)
    ).isEqualTo(BigDecimal.TEN);

    assertThat(
        BigDecimal.TEN.divide(BigDecimal.TEN)
    ).isEqualTo(BigDecimal.ONE);

  }

  @Test
  void test_19() {
    assertThat(
        BigDecimal.ONE
            .multiply(BigDecimal.TEN, new MathContext(1, RoundingMode.DOWN))
        .toString()
    ).isEqualTo("1E+1");

    assertThat(
        BigDecimal.ONE
            .multiply(BigDecimal.TEN, new MathContext(3, RoundingMode.DOWN))
    ).isEqualTo(BigDecimal.TEN);
  }

  @Test
  void test_20() {
        assertThat(
        BigDecimal.valueOf(12345.6789)
            .multiply(BigDecimal.TEN, new MathContext(2, RoundingMode.DOWN))
            .setScale(10)
    ).isEqualTo(BigDecimal.valueOf(120000));
  }

  @Test
  void test_21() {
    BigDecimal example = BigDecimal.valueOf(100.00001);
    assertThat(
        example.scale()
    ).isEqualTo(5);

    assertThat(
        example.precision()
    ).isEqualTo(8);

    assertThat(
        example.toString()
    ).isEqualTo("100.00001");
  }

  @Test
  void test_22() {
    BigDecimal example = BigDecimal.valueOf(0.00001);
    assertThat(
        example.scale()
    ).isEqualTo(6);

    assertThat(
        example.precision()
    ).isEqualTo(2);

    assertThat(
        example.toString()
    ).isEqualTo("0.000010");
  }
}
