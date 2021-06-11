package kirimaru.biz.domain;

import java.util.ArrayList;
import java.util.List;

public class NumToNumArray {

  @Deprecated(since = "遅い")
  public List<Integer> toArray(int i) {

    List<Integer> list = new ArrayList<>();
    for (char c : String.valueOf(i).toCharArray()) {
      list.add(Integer.parseInt(String.valueOf(c)));
    }

    return list;
  }


  public int[] toArray2(int i) {
    return String.valueOf(i).chars().map(e -> e - 48).toArray();
  }

  public List<Integer> toArrayReverseOrder(int i) {

    List<Integer> list = new ArrayList<>();
//    List<Integer> list = new ArrayList<>(String.valueOf(i).length()); // 初期配列用意してる時間の方が長い
    while (i != 0) {
      list.add(i % 10);
      i /= 10;
    }

    return list;
  }

  public int toIntReverseOrder(List<Integer> integers) {
    int tmp = 0;
    for (int i = 0; i < integers.size(); i++) {
      tmp += integers.get(i) * Math.pow(10, i);
    }
    return tmp;
  }
}
