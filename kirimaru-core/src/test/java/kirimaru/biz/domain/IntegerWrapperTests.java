package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class IntegerWrapperTests {
  @Nested
  class ToBuilder {
    IntegerWrapper integerWrapper = new IntegerWrapper();
    @Test
    void test_01() {
      IntegerWrapper target = IntegerWrapper.builder().build();

      assertThat(target).isEqualTo(integerWrapper);
    }

    @Test
    void test_02() {
      // バージョンによってはなぜかこれで0が設定されるバグがあった
      IntegerWrapper target = IntegerWrapper.builder().build();
      target = target.toBuilder().build();

      assertThat(target).isEqualTo(integerWrapper);
    }
  }

}