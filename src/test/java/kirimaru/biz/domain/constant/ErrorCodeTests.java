package kirimaru.biz.domain.constant;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Null;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ErrorCodeTests {
  @Nested
  class FillMessages {
    @Test
    void test_01() {
      assertThat(
          ErrorCode.E0001.fillMessages()
      ).isEqualTo(ErrorCode.E0001.getMessage());
    }

    @Test
    void test_02() {
      assertThat(
          ErrorCode.E0002.fillMessages("ゴリラ")
      ).isEqualTo("ゴリラは必須です。");
    }

    @Test
    void test_03() {
      assertThatThrownBy(
          () -> ErrorCode.E0002.fillMessages("ゴリラ", "キリン")
      ).isInstanceOf(RuntimeException.class);
    }
    @Test
    void test_04() {
      assertThatThrownBy(
          () -> ErrorCode.E0003.fillMessages("ゴリラ", null, null)
      ).isInstanceOf(NullPointerException.class);
    }
    @Test
    void test_05() {
      assertThatThrownBy(
          () -> ErrorCode.E0003.fillMessages(null, null, null)
      ).isInstanceOf(NullPointerException.class);
    }
  }

}
