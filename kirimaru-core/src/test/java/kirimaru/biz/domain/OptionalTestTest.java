package kirimaru.biz.domain;

import groovy.util.logging.Slf4j;
import kirimaru.annotation.OptionalTest;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@Slf4j
class OptionalTestTest {

  @Nested
  class test {

    @Test
    void test_01() {
      assert 1 == 1;
    }

    @OptionalTest
    void test_02() {
      System.out.println("うごいてしまっている！");
      assert 1 == 1;
    }
  }
}