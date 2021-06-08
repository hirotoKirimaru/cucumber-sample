package kirimaru.biz.domain;

/**
 * コラッツの問題、コラッツの予想
 */
public class CollatzProblem {

  public int apply(int i) {
    while (true) {
      i = notOne(i);
      if (i == 1) {
        return 1;
      }
    }

  }

  public int notOne(int i) {
    if (i % 2 == 0) {
      return i / 2;
    } else {
      return i * 3 + 1;
    }
  }


}
