package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class CollatzProblemTests {
  CollatzProblem target = new CollatzProblem();

  @Nested
  class apply {
    @Test
    void test_01() {
      assertThat(target.apply(10)).isEqualTo(1);
    }

    @Test
    void test_02() {
      assertThat(target.apply(333)).isEqualTo(1);
    }

    @Test
    void test_03() {
      assertThat(target.apply(1)).isEqualTo(1);
    }
  }


    @Nested
  class NotOne{
    @Test
    void test_01(){
      assertThat(target.notOne(10)).isEqualTo(5);
    }

    @Test
    void test_02(){
      assertThat(target.notOne(11)).isEqualTo(34);
    }
  }

}
