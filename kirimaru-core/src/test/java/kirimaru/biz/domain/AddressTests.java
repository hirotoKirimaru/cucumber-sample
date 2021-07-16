package kirimaru.biz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddressTests {


  @DisplayName("" +
      "[Given]" +
      "・ASCIIのハイフンを含む文字がある" +
      "[When]" +
      "・computeAddress1を呼び出す" +
      "[Then]" +
      "・ASCIIのハイフンに変換する" +
      "・呼び出し元変数は変換しない")
  @Test
  void test_01() {
    var target = Address.builder()
        .address1("東京都大田区12-34-56")
        .build();

    assertThat(target.computeAddress1())
        .isEqualTo("東京都大田区12-34-56");
    assertThat(target.getAddress1())
        .isEqualTo("東京都大田区12-34-56");
  }

  @DisplayName("" +
      "[Given]" +
      "・全角長音を含む文字がある" +
      "[When]" +
      "・computeAddress1を呼び出す" +
      "[Then]" +
      "・ASCIIのハイフンに変換する" +
      "・呼び出し元変数は変換しない")
  @Test
  void test_02() {
    var target = Address.builder()
        .address1("東京都大田区12ー34ー56")
        .build();

    assertThat(target.computeAddress1())
        .isEqualTo("東京都大田区12-34-56");
    assertThat(target.getAddress1())
        .isEqualTo("東京都大田区12ー34ー56");
  }

  @MethodSource(value = "param")
  @ParameterizedTest(name = "{0}を{1}に変換する")
  void parameterized_test(String before, String after){
     var target = Address.builder()
        .address1(before)
        .build();

    assertThat(target.computeAddress1()).isEqualTo(after);
  }

  private static Stream<Arguments> param() {
    return Stream.of(
        Arguments.of("-", "-"),
        Arguments.of("ー", "-")
    );
  }

  @Test
  void test_03() {
    var target = Address.builder()
        .address1("東京都大田区12ー34ー56")
        .build();

    assertThat(target.extracted(target.getAddress1(), List.of("-", "ー")))
        .isEqualTo("東京都大田区12-34-56");
    assertThat(target.getAddress1())
        .isEqualTo("東京都大田区12ー34ー56");
  }
}
