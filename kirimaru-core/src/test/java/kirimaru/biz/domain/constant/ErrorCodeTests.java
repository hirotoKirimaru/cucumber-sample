package kirimaru.biz.domain.constant;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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


    @Disabled("MessageFormatだと2倍くらいかかる")
    @Nested
    class 性能チェック {
      private final String hoge = "{0}が{1}の時、{2}は必須です。";
      private final MessageFormat fuga = new MessageFormat("{0}が{1}の時、{2}は必須です。");
      @Test
      void test_01() {
        log.info("*******************");
        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
          fuga.format("1", "2", "3");
        }
        long l1 = System.currentTimeMillis() - l;
        log.info("staticじゃない:" + l1);
        // 3755
      }

      @Test
      void test_02() {
        log.info("*******************");
        long a = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
          MessageFormat.format(hoge, "1", "2", "3");
        }
        long l2 = System.currentTimeMillis() - a;
        log.info("static:" + l2);
        // 7861
      }
    }

  }

}
