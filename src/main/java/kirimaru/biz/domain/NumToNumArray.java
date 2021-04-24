package kirimaru.biz.domain;

import java.util.ArrayList;
import java.util.List;

public class NumToNumArray {

  public List<Integer> toArrayNoOrder(int i) {

    List<Integer> list = new ArrayList<>();
    for (char c : String.valueOf(i).toCharArray()) {
      list.add(Integer.parseInt(String.valueOf(c)));
    }

    return list;
  }
}
