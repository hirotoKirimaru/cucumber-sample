package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Stringに生えているのはJava15から。 Formatter自体は昔からある。
 */
class FormattedTests {

  @Test
  void test_01() {
    String actual = "aaa%s".formatted("bbb");

    assertThat(actual).isEqualTo("aaabbb");
  }

  @Test
  void test_02() {
    String actual = "aaa%010d".formatted(1);

    assertThat(actual).isEqualTo("aaa0000000001");
  }

  @Test
  void test_03() {
    assertThatThrownBy(
        () -> "aaa%010d".formatted("111")
    ).isInstanceOf(java.util.IllegalFormatConversionException.class);
  }

  @Test
  void test_04() {
    String actual = "%s様".formatted(null);

    assertThat(actual).isEqualTo("null様");
  }

  @Test
  void test_05() {
    String actual = "%s様".formatted("きり丸");

    assertThat(actual).isEqualTo("きり丸様");
  }


  @Test
  void test_06() {
    String actual =
        """
            %s様
                
            貴方だけに素晴らしい商品を用意しました！
            """.formatted("きり丸");

    String expected =
        """
            きり丸様
                    
            貴方だけに素晴らしい商品を用意しました！
            """;
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void test_07() {
    String actual =
        """
            %3$s様
                
            貴方だけに素晴らしい商品を用意しました！
            商品1： %2$s
            商品2： %1$s
            """.formatted("きり丸", "りんご", "ゴリラ");

    String expected =
        """
            ゴリラ様
                    
            貴方だけに素晴らしい商品を用意しました！
            商品1： りんご
            商品2： きり丸
            """;
    assertThat(actual).isEqualTo(expected);
  }

//  @CsvSource(value = {
//      "%%,'','%'",
//      "%d,'123','123'",
//      "%o,'123','173'",
//  })
//  @ParameterizedTest()
//  public void test_format(String format, String param, String expect) {
//    String actual = format.formatted(param);
//
//    assertThat(actual).isEqualTo(expect);
//
//  }
}
