package kirimaru.biz.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class NumToNumArrayTest {
  NumToNumArray numToNumArray = new NumToNumArray();

  @Nested
  class ToArrayNoOrder {

    @Test
    void test_01() {
      assertThat(
          numToNumArray.toArrayNoOrder(109)
      ).containsExactlyInAnyOrder(1, 0, 9);
    }
  }

  @Nested
  class ToArrayNoOrder2 {

    @Test
    void test_01() {
      assertThat(
          numToNumArray.toArrayNoOrder2(109)
      ).containsExactlyInAnyOrder(1, 0, 9);
    }
  }




  @Disabled("CIでは毎回回したくない")
  @Nested
  class 性能チェック {
    @Test
    void test_01() {

      long start = System.currentTimeMillis();

      for (int i = 0; i < 100000000; i++) {
        numToNumArray.toArrayNoOrder(i);
      }

      long end = System.currentTimeMillis();

      System.out.println(end - start);
      // 8034弱
    }

    @Test
    void test_02() {

      long start = System.currentTimeMillis();

      for (int i = 0; i < 100000000; i++) {
        numToNumArray.toArrayNoOrder2(i);
      }

      long end = System.currentTimeMillis();

      System.out.println(end - start);
      // 4263弱
    }

  }
}
