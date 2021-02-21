package jp.co.kelly.biz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TwoArrayTests {

  @DisplayName("AとBの重複した項目のみを出力する")
  @Test
  void test_01() {
    int[] array1 = new int[]{2, 2, 3, 5};
    int[] array2 = new int[]{1, 2, 5};

    int[] actual = Arrays.stream(array1)
        .filter(
            i -> Arrays.stream(array2).anyMatch(j -> i == j)
        ).distinct()
        .toArray();

    int[] expected = new int[]{2, 5};
    assertThat(actual).isEqualTo(expected);
  }
}