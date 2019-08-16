package jp.co.kelly.biz.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneHundredDoorsTest {
  OneHundredDoors target;

  @BeforeEach
  void setup() {
    target =
        OneHundredDoors.builder()
            .doors(new boolean[100])
            .build();
  }


  @DisplayName("executeの動作チェック")
  @Nested
  class execute {
    @DisplayName("1")
    @Test
    void test_01() {

      boolean[] expect = new boolean[100];
      target.execute();
      assertAll(
//          ()-> assertEquals(expect, target.getDoors(),"雑比較"),
          () -> assertEquals(true, target.getDoors()[0], "雑比較")

      );
    }


  }

  @Nested
  @DisplayName("toggleの動作チェック")
  class toggle {
    @DisplayName("1")
    @Test
    void test_01() {
      assertAll(
          () -> assertEquals(false, target.getDoors()[0], "0"),
          () -> assertEquals(false, target.getDoors()[1], "1"),
          () -> assertEquals(false, target.getDoors()[2], "2"),
          () -> assertEquals(false, target.getDoors()[3], "3")
      );

      target.toggle(1);
      assertAll(
          () -> assertEquals(true, target.getDoors()[0], "0"),
          () -> assertEquals(true, target.getDoors()[1], "1"),
          () -> assertEquals(true, target.getDoors()[2], "2"),
          () -> assertEquals(true, target.getDoors()[3], "3")
      );
    }

    @DisplayName("2")
    @Test
    void test_02() {
      assertAll(
          () -> assertEquals(false, target.getDoors()[0], "0"),
          () -> assertEquals(false, target.getDoors()[1], "1"),
          () -> assertEquals(false, target.getDoors()[2], "2"),
          () -> assertEquals(false, target.getDoors()[3], "3")
      );

      target.toggle(2);
      assertAll(
          () -> assertEquals(false, target.getDoors()[0], "0"),
          () -> assertEquals(true, target.getDoors()[1], "1"),
          () -> assertEquals(false, target.getDoors()[2], "2"),
          () -> assertEquals(true, target.getDoors()[3], "3")
      );
    }

    @DisplayName("3")
    @Test
    void test_03() {
      assertAll(
          () -> assertEquals(false, target.getDoors()[0], "0"),
          () -> assertEquals(false, target.getDoors()[1], "1"),
          () -> assertEquals(false, target.getDoors()[2], "2"),
          () -> assertEquals(false, target.getDoors()[3], "3")
      );

      target.toggle(3);
      assertAll(
          () -> assertEquals(false, target.getDoors()[0], "0"),
          () -> assertEquals(false, target.getDoors()[1], "1"),
          () -> assertEquals(true, target.getDoors()[2], "2"),
          () -> assertEquals(false, target.getDoors()[3], "3")
      );
    }

  }


}