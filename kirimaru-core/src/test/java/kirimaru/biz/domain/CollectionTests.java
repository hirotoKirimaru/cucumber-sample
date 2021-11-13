package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CollectionTests {

  @AllArgsConstructor
  @Data
  public static class Config {

    private String str;
  }

  @Test
  @DisplayName("""
      [When]
      ・1,2,3の配列を渡す
      [Then]
      ・偶数は+100する
      ・奇数は+50する
      ・51, 102, 53の配列に変換する
      """)
  void test_01() {
    var base = Stream.of(1, 2, 3);

    var actual =
        base.map(e -> {
          if (e % 2 == 0) {
            return e + 100;
          } else {
            return e + 50;
          }
        }).collect(Collectors.toList());

    assertThat(actual).isEqualTo(Arrays.asList(51, 102, 53));
  }

  @DisplayName("""
      peekを使った副作用の検証
      ※ 想定通りにいかない
      """)
  @Test
  void test_02() {
    var actual =
        Stream.of(
                new StringBuilder("1"),
                new StringBuilder("2"),
                new StringBuilder("3"))
            .peek(e -> e.append("-count"))
            .map(StringBuilder::toString)
            .collect(Collectors.toList());

    assertThat(actual).isEqualTo(
        List.of("1-count", "2-count", "3-count")
    );
  }

  @Test
  void test_03() {
    var actual =
        Stream.of(
                new Config("1"),
                new Config("2"),
                new Config("3"))
            .peek(e -> e.setStr(e.getStr() + "-count"))
            .map(Config::getStr)
            .collect(Collectors.toList());

    assertThat(actual).isEqualTo(
        List.of("1-count", "2-count", "3-count")
    );
  }
}
