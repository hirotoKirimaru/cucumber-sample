package com.example.demo.domain;

import lombok.*;

@Data
@Builder
public class OneHundredDoors {

  private boolean[] doors;

  public void toggle(int i) {
    for (int a = i - 1; a < doors.length; a += i) {
      doors[a] = !doors[a];
    }
  }
}
