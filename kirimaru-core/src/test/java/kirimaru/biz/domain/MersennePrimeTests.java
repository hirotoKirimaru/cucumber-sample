package kirimaru.biz.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * メルセンヌ素数とは.
 * <p>
 * https://ja.wikipedia.org/wiki/%E3%83%A1%E3%83%AB%E3%82%BB%E3%83%B3%E3%83%8C%E6%95%B0
 * </p>
 */
class MersennePrimeTests {

  static class Target {

    /**
     * 素数であり、メルセンヌ数(2^J-1)を満たす値のことをメルセンヌ素数と呼ぶ
     *
     * @param value 値
     * @return 0=メルセンヌ素数ではない, 1=メルセンヌ素数
     */
    public static int isMersennePrime(int value) {
      if (isMersenne(value) && isPrime(value)) {
        return 1;
      }
      return 0;
    }

    /**
     * 2^j-1が整数かどうか
     *
     * @param value 値
     */
    private static boolean isMersenne(int value) {
      return log2(value + 1) % 1 == 0;
    }

    /**
     * 底が2のlog
     *
     * @param value 値
     */
    public static double log2(int value) {
      return Math.log(value) / Math.log(2);
    }

    /**
     * 素数判定.
     *
     * @param value 値
     */
    public static boolean isPrime(int value) {
      if (value <= 1) {
        return false;
      }
      if (value <= 3) {
        return true;
      }
      for (int i = 2; i <= Math.sqrt(value); i++) {
        if (value % i == 0) {
          return false;
        }
      }

      return true;
    }
  }

  @MethodSource(value = "mersennePrimeParam")
  @ParameterizedTest(name = "{0}は{1}")
  void mersennePrime(int value, int rtn) {
    assertThat(Target.isMersennePrime(value)).isEqualTo(rtn);
  }

  private static Stream<Arguments> mersennePrimeParam() {
    return Stream.of(
        Arguments.of(1, 0),
        Arguments.of(2, 0),
        Arguments.of(3, 1),
        Arguments.of(4, 0),
        Arguments.of(5, 0),
        Arguments.of(6, 0),
        Arguments.of(7, 1),
        Arguments.of(8, 0),
        Arguments.of(30, 0),
        Arguments.of(31, 1),
        Arguments.of(32, 0),
        Arguments.of(126, 0),
        Arguments.of(127, 1),
        Arguments.of(128, 0)
    );
  }


  @MethodSource(value = "primeParam")
  @ParameterizedTest(name = "{0}は{1}")
  void prime(int value, boolean rtn) {
    assertThat(Target.isPrime(value)).isEqualTo(rtn);
  }

  private static Stream<Arguments> primeParam() {
    return Stream.of(
        Arguments.of(1, false),
        Arguments.of(2, true),
        Arguments.of(3, true),
        Arguments.of(4, false),
        Arguments.of(5, true),
        Arguments.of(6, false),
        Arguments.of(7, true),
        Arguments.of(8, false),
        Arguments.of(30, false),
        Arguments.of(31, true),
        Arguments.of(32, false),
        Arguments.of(126, false),
        Arguments.of(127, true),
        Arguments.of(128, false)
    );
  }
}
