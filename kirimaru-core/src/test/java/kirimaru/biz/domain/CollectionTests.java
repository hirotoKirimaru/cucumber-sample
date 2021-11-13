package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.BaseStream;
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
  void test_01_01() {
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

  @Test
  @DisplayName("""
      [When]
      ・1,2,3の配列を渡す
      [Then]
      ・偶数は+100する
      ・奇数は+50する
      ・合計値102, 51, 53を返却する
      """)
  void test_01_02() {
    Supplier<Stream<Integer>> base = () -> Stream.of(1, 2, 3);

    var evenList =
        base.get()
            .filter(e -> e % 2 == 0)
            .map(e -> e + 100);

    var oddList =
        base.get()
            .filter(e -> e % 2 != 0)
            .map(e -> e + 50);

    var actual = Stream.concat(evenList, oddList);

    assertThat(actual.collect(Collectors.toList()))
        .isEqualTo(Arrays.asList(102, 51, 53));
  }

  @Test
  @DisplayName("""
      [When]
      ・1,2,3の配列を渡す
      [Then]
      ・偶数は+100する
      ・奇数は+50する
      ・合計値204を返却する
      """)
  void test_01_03() {
    Supplier<Stream<Integer>> base = () -> Stream.of(1, 2, 3);

    var evenSum =
        base.get()
            .filter(e -> e % 2 == 0)
            .mapToInt(e -> e + 100)
            .sum();

    var oddSum =
        base.get()
        .filter(e -> e % 2 != 0)
        .mapToInt(e -> e + 50)
        .sum();

    var actual = evenSum + oddSum;

    assertThat(actual).isEqualTo(204);
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
