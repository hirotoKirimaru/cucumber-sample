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
//    assertEquals(true, target.doors[0]);
    assertEquals(false, target.doors[0]);
  }

}