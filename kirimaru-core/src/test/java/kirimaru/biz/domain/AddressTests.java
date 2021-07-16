package kirimaru.biz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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



}
