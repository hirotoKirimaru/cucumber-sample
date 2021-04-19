package kirimaru.biz.domain.constant;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Null;

import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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

    private final String hoge = "{0}が{1}の時、{2}は必須です。";

    @Disabled("MessageFormatだと2倍くらいかかる")
    @Test
    void test_06() {
      log.info("*******************");
      long l = System.currentTimeMillis();
      for (int i = 0; i < 10000000; i++) {
        ErrorCode.E0003.fillMessages("1", "2", "3");
      }
      long l1 = l - System.currentTimeMillis();
      log.info("staticじゃない:" + l1);
      log.info("*******************");
      log.info("*******************");
      long a = System.currentTimeMillis();
      for (int i = 0; i < 10000000; i++) {
        MessageFormat.format(hoge, "1", "2", "3");
      }
      long l2 = a - System.currentTimeMillis();
      log.info("static:" + l2);
      log.info("*******************");

      log.info("*******************");
      long b = System.currentTimeMillis();
      for (int i = 0; i < 10000000; i++) {
        ErrorCode.E0003.fillMessages("1", "2", "3");
      }
      long l3 = b - System.currentTimeMillis();
      log.info("再度：staticじゃない:" + l3);
      log.info("*******************");

    }
  }

}
