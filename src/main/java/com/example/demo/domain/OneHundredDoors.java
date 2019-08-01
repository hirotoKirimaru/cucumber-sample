package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OneHundredDoors {
  public boolean[] doors;

  public void toggle(int i) {
    for (int a = 0; a < doors.length; a += i) {
      doors[a] = !doors[a];
    }
  }
}
