package kirimaru.biz.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OneHundredDoors {

  private boolean[] doors;

  public void toggle(int i) {
    for (int a = i - 1; a < doors.length; a += i) {
      doors[a] = !doors[a];
    }
  }

  public void execute() {
    for (int i = 1; i <= doors.length; i++) {
      toggle(i);
    }
  }
}
