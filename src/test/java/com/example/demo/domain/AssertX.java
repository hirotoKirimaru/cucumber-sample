package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class AssertX {
  List<User> list =
      Arrays.asList(
          User.builder().name("乱太郎").age("10").sex("1").build(),
          User.builder().name("きり丸").age("9").sex("1").build(),
          User.builder().name("新兵衛").age("8").sex("2").build()
      );

  @Test
  void _assertEquals() {
    assertAll(
        () -> assertEquals(1, list.size(), "size"),
        () -> assertEquals(null, list.get(0), "乱太郎"),
        () -> assertEquals(null, list.get(1), "きり丸"),
        () -> assertEquals(null, list.get(2), "新兵衛")
    );
  }

  @Test
  void _assertThat() {
    assertAll(
        () -> assertThat(list.size()).as("size").isEqualTo(1),
        () -> assertThat(list.get(0)).as("乱太郎").isEqualTo(null),
        () -> assertThat(list.get(1)).as("きり丸").isEqualTo(null),
        () -> assertThat(list.get(2)).as("新兵衛").isEqualTo(null)

    );
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  private static class User {
    String name;
    String age;
    String sex;
  }

}