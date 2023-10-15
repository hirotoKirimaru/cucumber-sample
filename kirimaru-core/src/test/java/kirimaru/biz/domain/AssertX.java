package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Disabled
class AssertX {
  List<Ninja> list =
      Arrays.asList(
          Ninja.builder().name("乱太郎").age("10").sex("1").build(),
          Ninja.builder().name("きり丸").age("9").sex("1").build(),
          Ninja.builder().name("新兵衛").age("8").sex("2").build()
      );
  Ninja RANTARO =
      Ninja.builder().name("乱太郎").age("10").sex("1").build();

  Ninja KIRIMARU =
      Ninja.builder().name("きり丸").age("9").sex("1").build();

  Ninja SHINBE =
      Ninja.builder().name("新兵衛").age("8").sex("2").build();

  @Disabled
  @Test
  void _assertEquals() {
    assertAll(
        () -> assertEquals(1, list.size(), "size"),
        () -> assertEquals(null, list.get(0), "乱太郎"),
        () -> assertEquals(null, list.get(1), "きり丸"),
        () -> assertEquals(null, list.get(2), "新兵衛")
    );
  }

  @Disabled
  @Test
  void _assertThat() {
    // 特徴: 1度でassertできる。
    // しかし、途中でfalseになった場合、以降の処理が検証されない。
    // assertAllでくくった場合、検証メソッドの数だけ処理を繰り返す。
    // ただし、name, age, sexの検証メソッドをそれぞれ呼ぶのではなく、
    // nameとageとsexの検証セットを3回繰り返す。
    // 同じエラーメッセージが何度も発生するのでうざったいし、後続が処理されない。
    assertThat(list.get(0)).as("乱太郎")
        .hasFieldOrPropertyWithValue("name", "乱太郎")
        .hasFieldOrPropertyWithValue("age", "10")
        .hasFieldOrPropertyWithValue("sex", "1");

    // assertThatなら、softAssertionsを使う方がよさそうだ。
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(list).hasSize(1);
    softly.assertThat(list.get(0)).as("乱太郎")
        .isEqualTo(null);
//        .hasFieldOrPropertyWithValue("name", "リンターロ")
//        .hasFieldOrPropertyWithValue("age", "")
//        .hasFieldOrPropertyWithValue("sex", "");

    softly.assertThat(list.get(1)).as("きり丸").isEqualTo(null);
    softly.assertThat(list.get(2)).as("新兵衛").isEqualTo(null);
    softly.assertAll();

  }

  @DisplayName("assertThatの検証2")
  @Test
  void _assertThat2() {
    assertAll(
        () -> assertThat(list.get(0)).as("乱太郎")
            .hasFieldOrPropertyWithValue("name", null)
            .hasFieldOrPropertyWithValue("age", null)
            .hasFieldOrPropertyWithValue("sex", null)
    );
  }

  @Test
  void _assertThatThrownBy() {

    SoftAssertions softly = new SoftAssertions();
    softly.assertThatThrownBy(
        () -> list.get(999)
    ).isInstanceOfSatisfying(ArrayIndexOutOfBoundsException.class,
        e -> assertEquals(e.getMessage(), "Index 999 out of bounds for length 3")
    );

    softly.assertAll();
  }


  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  private static class Ninja {
    String name;
    String age;
    String sex;
  }

}
