package kirimaru.biz.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HodderTests {

  static class Target {

    /**
     * 素数であり、2^J-1を満たす値のことをHodder Number(ホダー数)と呼ぶ
     *
     * @param value 値
     * @return 0=ホダー数ではない, 1=ホダー数
     */
    public static int isHodder(int value) {
      if (isPrime(value) && isCond(value)) {
        return 1;
      }
      return 0;
    }

    /**
     * 2^j-1が整数かどうか
     * @param value 値
     */
    private static boolean isCond(int value) {
      return log2(value + 1) % 1 == 0;
    }

    /**
     * 底が2のlog
     * @param value 値
     */
    public static double log2(int value) {
      return Math.log(value) / Math.log(2);
    }

    /**
     * 素数判定.
     * @param value 値
     */
    public static boolean isPrime(int value) {
      if (value <= 1) {
        return false;
      }
      if (value <= 3) {
        return true;
      }
      // 3以上の奇数について判定
      for (int i = 3; i <= Math.sqrt(value); i += 2) {
        if (value % i == 0) {
          return false;
        }
      }

      return true;
    }
  }

  @MethodSource(value = "param")
  @ParameterizedTest(name = "{0}は{1}")
  void parameterized_test(int value, int rtn) {
    assertThat(Target.isHodder(value)).isEqualTo(rtn);
  }

  private static Stream<Arguments> param() {
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
}
