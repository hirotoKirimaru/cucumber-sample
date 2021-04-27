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
  class ToArray2 {

    @Test
    void test_01() {
      assertThat(
          numToNumArray.toArray2(109)
      ).isEqualTo(new int[]{1, 0, 9});
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

  @Nested
  class ToIntReverseOrder {

    @Test
    void test_01() {
      assertThat(
          numToNumArray.toIntReverseOrder(List.of(9, 0, 1))
      ).isEqualTo(109);
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
      // 8941弱
    }

    @Test
    void test_02() {

      long start = System.currentTimeMillis();

      for (int i = 0; i < 100000000; i++) {
        numToNumArray.toArrayReverseOrder(i);
      }

      long end = System.currentTimeMillis();

      System.out.println(end - start);
      // 4227弱
    }

    class Measure {
      long start;
      long end;

      public void start() {
        this.start = System.currentTimeMillis();
      }

      public void end() {
        this.end = System.currentTimeMillis();

        System.out.println(this.end - this.start);
      }
    }

    @Test
    void test_03() {

      long start = System.currentTimeMillis();

      for (int i = 0; i < 100000000; i++) {
        numToNumArray.toArray2(i);
      }

      long end = System.currentTimeMillis();

      System.out.println(end - start);
      // 8359弱
    }

  }
}
