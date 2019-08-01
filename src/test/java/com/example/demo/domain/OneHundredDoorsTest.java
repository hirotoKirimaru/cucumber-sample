package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneHundredDoorsTest {


  @Test
  void toggle() {
    OneHundredDoors target =
        OneHundredDoors
            .builder()
            .doors(new boolean[100])
            .build();

    target.toggle(1);
    assertEquals(true, target.doors[0]);
    assertAll(
        () -> assertEquals(false, target.doors[1], "1"),
        () -> assertEquals(false, target.doors[60], "2"),
        () -> assertEquals(false, target.doors[70], "3")

    );

//    assertEquals(false, target.doors[0]);
  }

}