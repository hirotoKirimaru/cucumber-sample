package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.IllegalFormatConversionException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class AppStringTest {

  @Nested
  class of {

  }

  @TestInstance(Lifecycle.PER_CLASS)
  @Nested
  class isIllegal {

    @MethodSource(value = "target")
    @ParameterizedTest
    public void test(String value, boolean expected) {
      assertThat(
          AppString.isIllegal(value)
      ).isEqualTo(expected);
    }

    public Stream<Arguments> target() {
      return Stream.of(
          Arguments.of(null, false),
          Arguments.of("あいうえお", false),
          Arguments.of("吉野家", false), // きち
          Arguments.of("𠮷野家", true), // つちよし
          Arguments.of("🥺", true),
          Arguments.of("あい🥺うえお", true),
          Arguments.of("∥", true), // MS932, UTF-8なら問題ない
          Arguments.of("あい∥うえお", true) // MS932, UTF-8なら問題ない

      );
    }
  }


  @Nested
  class toString {

    @Test
    void test_01() {
      assertThat(
          AppString.of(null).toString()
      ).isEqualTo("");
    }
  }

  @Nested
  class IllegalByteCount {

    @Test
    void test_01() {
      assertThat(
          AppString.of("1111").illegalByteCount(4)
      ).isFalse();
    }

    @Test
    void test_02() {
      assertThat(
          AppString.of("1111").illegalByteCount(3)
      ).isTrue();
    }

    @Test
    void test_03() {
      assertThat(
          AppString.of("吉野家").illegalByteCount(9)
      ).isFalse();
    }

    @Test
    void test_04() {
      assertThat(
          AppString.of("吉野家").illegalByteCount(8)
      ).isTrue();
    }
  }

  @Nested
  class IllegalWordCount {

    @Test
    void test_01() {
      assertThat(
          AppString.of("1111").illegalWordCount(4)
      ).isFalse();
    }

    @Test
    void test_02() {
      assertThat(
          AppString.of("1111").illegalWordCount(3)
      ).isTrue();
    }

    @Test
    void test_03() {
      assertThat(
          AppString.of("吉野家").illegalWordCount(3)
      ).isFalse();
    }

    @Test
    void test_04() {
      assertThat(
          AppString.of("吉野家").illegalWordCount(2)
      ).isTrue();
    }
  }


  @Nested
  class PadLeft {

    @Test
    void test_01() {
      assertThat(
          String.format("%03d", 1)
      ).isEqualTo("001");
    }

    @Test
    void test_02() {
      // Java15から導入
      assertThat(
          "%03d".formatted(1)
      ).isEqualTo("001");
    }

    @Test
    void test_02_02() {
      // formatが合わないとエラーがでる
      assertThatThrownBy(
          () -> "%03d".formatted("1")
      ).isInstanceOf(IllegalFormatConversionException.class);
    }

    @Test
    void test_03() {
      // もーちょっとちゃんとしたやつないのかな・・・
      assertThat(
          "%3s".formatted(1).replace(" ", "X")
      ).isEqualTo("XX1");
    }
  }

  @Nested
  class PadRight {

    @Test
    @Disabled("ずれるし、むりか")
    void test_01() {
      // 右0埋めを数字ですることは無理みたい
      assertThat(
          "%0-3d".formatted("1")
      ).isEqualTo("100");
    }

    @Test
    void test_02() {
      assertThat(
          "%-3s".formatted(1).replace(" ", "X")
      ).isEqualTo("1XX");
    }
  }
}