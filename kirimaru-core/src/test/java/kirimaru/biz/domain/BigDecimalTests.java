package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
  void test_04_02() {
    List<BigDecimal> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(BigDecimal.ONE);
    }

    assertThat(
        list.stream().reduce(
            BigDecimal.TEN, BigDecimal::subtract
        )
    ).isEqualTo(BigDecimal.ZERO);
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

    assertThat(
        BigDecimal.valueOf(10.11)
            .remainder(BigDecimal.valueOf(3))
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
        BigDecimal.ONE.movePointRight(1).toPlainString()
    ).isEqualTo("10");

    assertThat(
        BigDecimal.TEN.movePointLeft(1).toPlainString()
    ).isEqualTo("1.0");

    assertThat(
        BigDecimal.ONE.movePointLeft(3).toPlainString()
    ).isEqualTo("0.001");

    assertThat(
        BigDecimal.ONE.multiply(BigDecimal.TEN).toPlainString()
    ).isEqualTo("10");

    assertThat(
        BigDecimal.TEN.divide(BigDecimal.TEN).toPlainString()
    ).isEqualTo("1");

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
            .toPlainString()
    ).isEqualTo("120000");

    assertThat(
        BigDecimal.valueOf(12345.6789)
            .multiply(BigDecimal.TEN)
            .toPlainString()
    ).isEqualTo("123456.7890");
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

  @Test
  void test_23() {
    // 単純に1 / 3 の割り算する. 割り切れないとArithmeticExceptionが発生する.
    assertThatThrownBy(
        () -> BigDecimal.ONE.divide(BigDecimal.valueOf(3))
    ).isInstanceOfSatisfying(
        ArithmeticException.class,
        (e) -> e.getMessage().equals("Non-terminating decimal expansion; no exact representable decimal result.")
    );

    // 1 / 3 の割り算をした後に、第二引数のscaleで丸める(切り上げする)
    assertThat(
        BigDecimal.ONE
            .divide(BigDecimal.valueOf(3), 3, RoundingMode.UP)
            .toPlainString()
    ).isEqualTo("0.334");

    // 1 / 3 の割り算をする前に精度を増やしておき、増やした精度で丸める(切り上げする)
    assertThat(
        BigDecimal.ONE
            .setScale(3, RoundingMode.UP)
            .divide(BigDecimal.valueOf(3), RoundingMode.UP)
            .toPlainString()
    ).isEqualTo("0.334");

    // 1 / 3 の割り算をした後に、MathContextの有効数字で丸める（切り上げする）
    assertThat(
        BigDecimal.ONE
            .divide(BigDecimal.valueOf(3), new MathContext(3, RoundingMode.UP))
            .toPlainString()
    ).isEqualTo("0.334");

    assertThat(
        BigDecimal.ONE
            .divide(BigDecimal.valueOf(3), RoundingMode.UP)
            .toPlainString()
    ).isEqualTo("1");

    assertThat(
        BigDecimal.ONE
            .divide(BigDecimal.valueOf(3), 3, RoundingMode.UP)
            .multiply(BigDecimal.valueOf(100))
            .toPlainString()
    ).isEqualTo("33.400");

    assertThat(
        BigDecimal.ONE
            .divide(BigDecimal.valueOf(3), 6, RoundingMode.UP)
            .multiply(BigDecimal.valueOf(100))
            .setScale(3, RoundingMode.UP)
            .toPlainString()
    ).isEqualTo("33.334");
  }

  @Test
  void test_24() {
    // 10 > 1の時は1
    assertThat(
       BigDecimal.TEN.compareTo(BigDecimal.ONE)
    ).isEqualTo(1);

    // 1 < 10の時は-1
    assertThat(
        BigDecimal.ONE.compareTo(BigDecimal.TEN)
    ).isEqualTo(-1);

    // 一致している時は0
    // 0.0 と 0.00の比較
    assertThat(
        BigDecimal.ONE.setScale(1)
            .compareTo(BigDecimal.ONE.setScale(2))
    ).isEqualTo(0);
  }

  @Test
  void test_25() {

    assertThat(
        BigDecimal.ONE.setScale(1)
            .equals(BigDecimal.ONE.setScale(2))
    ).isEqualTo(false);
  }

  @Test
  void test_26() {
    assertThat(
        BigDecimal.ZERO
            .compareTo(BigDecimal.ZERO.setScale(2))
    ).isEqualTo(0);
    assertThat(
        BigDecimal.ZERO.signum()
    ).isEqualTo(0);
  }

  @Test
  void test_27() {
    assertThat(
        new BigDecimal("1.00000000000").scale()
    ).isEqualTo(11);
    assertThat(
        new BigDecimal("1.00000000000").stripTrailingZeros().scale()
    ).isEqualTo(0);

    assertThat(
        new BigDecimal("1.00000000001").scale()
    ).isEqualTo(11);
    assertThat(
        new BigDecimal("1.00000000001").stripTrailingZeros().scale()
    ).isEqualTo(11);
  }
}
