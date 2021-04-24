package kirimaru.biz.domain;

import java.util.ArrayList;
import java.util.List;

public class NumToNumArray {

  @Deprecated(since = "遅い")
  public List<Integer> toArrayNoOrder(int i) {

    List<Integer> list = new ArrayList<>();
    for (char c : String.valueOf(i).toCharArray()) {
      list.add(Integer.parseInt(String.valueOf(c)));
    }

    return list;
  }

  public List<Integer> toArrayNoOrder2(int i) {

    List<Integer> list = new ArrayList<>();
    while (true) {
      list.add(i % 10);
      i /= 10;
      if (i == 0) {
        break;
      }
    }

    return list;
  }
}
