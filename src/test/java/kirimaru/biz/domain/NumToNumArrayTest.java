package kirimaru.biz.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class NumToNumArrayTest {
  NumToNumArray numToNumArray = new NumToNumArray();

  @Nested
  class ToArray {

    @Test
    void test_01() {
      assertThat(
          numToNumArray.toArray(109)
      ).isEqualTo(List.of(1, 0, 9));
    }
  }

  @Nested
  class ToArrayReverseOrder {

    @Test
    void test_01() {
      assertThat(
          numToNumArray.toArrayReverseOrder(109)
      ).isEqualTo(List.of(9, 0, 1));
    }
  }




  @Disabled("CIでは毎回回したくない")
  @Nested
  class 性能チェック {
    @Test
    void test_01() {

      long start = System.currentTimeMillis();

      for (int i = 0; i < 100000000; i++) {
        numToNumArray.toArray(i);
      }

      long end = System.currentTimeMillis();

      System.out.println(end - start);
      // 8034弱
    }

    @Test
    void test_02() {

      long start = System.currentTimeMillis();

      for (int i = 0; i < 100000000; i++) {
        numToNumArray.toArrayReverseOrder(i);
      }

      long end = System.currentTimeMillis();

      System.out.println(end - start);
      // 4263弱
    }

  }
}
