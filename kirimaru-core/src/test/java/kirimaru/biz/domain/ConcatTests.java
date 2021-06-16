package kirimaru.biz.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ConcatTests {
  Concat target = new Concat();
  List<String> param = List.of("りんご", "ゴリラ", "ラッパ");
  String expect = "りんご,ゴリラ,ラッパ";

  @Test
  void test_01() {
    assertThat(
        target.joiner(param)
    ).isEqualTo(expect);
  }

  @Test
  void test_02() {
    assertThat(
        target.builder(param)
    ).isEqualTo(expect);
  }

  @Test
  void test_03() {
    assertThat(
        target.builder2(param)
    ).isEqualTo(expect);
  }

  @Nested
  class Joiner {
    Concat.StringJoinerPractice target = new Concat.StringJoinerPractice();

    @Test
    void test_01() {
      assertThat(
          target.tab(param)
      ).isEqualTo("りんご\tゴリラ\tラッパ");
    }

    @Test
    void test_02() {
      assertThat(
          target.addAiueo(param)
      ).isEqualTo("りんごAIUEOゴリラAIUEOラッパ");
    }

    @Test
    void test_03() {
      assertThat(
          target.addBracket(param)
      ).isEqualTo("{りんご,ゴリラ,ラッパ}");
    }

    @Test
    void test_04() {
      assertThat(
          target.addBracketAndDoubleQuote(param)
      ).isEqualTo("{\"りんご\",\"ゴリラ\",\"ラッパ\"}");
    }

    @Test
    void test_05() {
      List list = new ArrayList();
      list.add(null);
      assertThat(
          target.tab(list)
      ).isEqualTo("null");
    }
  }
}
