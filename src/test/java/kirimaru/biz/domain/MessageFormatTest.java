package kirimaru.biz.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MessageFormatTest {

  @Test
  void test_01() {
    assertThat(
        MessageFormat.format(
            "{0}が{1}の時、{2}は必須です。",
            "契約者", "未成年", "保護者"
        )
    ).isEqualTo("契約者が未成年の時、保護者は必須です。");

  }

  @Test
  void test_02() {
    assertThat(
        new MessageFormat("{0}が{1}の時、{2}は必須です。")
            .format(new String[]{"契約者", "未成年", "保護者"})
    ).isEqualTo("契約者が未成年の時、保護者は必須です。");
  }

//  @Test
//  void test_03() {
//    assertThat(
//        new MessageFormat() // 初期でもフォーマットが必要なのかよ。
//            .format("{0}が{1}の時、{2}は必須です。","契約者", "未成年", "保護者")
//    ).isEqualTo("契約者が未成年の時、保護者は必須です。");
//  }


  @Nested
  class NumberFormat {

    @Test
    void test_01() {
      assertThat(
          MessageFormat.format("{0}", 123456)
      ).isEqualTo("123,456");
    }

    @Test
    void test_02() {
      assertThat(
          MessageFormat.format("{0,number,#}", 123456)
      ).isEqualTo("123456");
    }

    @Test
    void test_03() {
      assertThat(
          MessageFormat.format("{0,number,#.##}", 123456.123456)
      ).isEqualTo("123456.12");
    }

    @Test
    void test_04() {
      assertThat(
          MessageFormat.format("{0,number,#.##}", 123456.999)
      ).isEqualTo("123457");
    }

    @Test
    void test_05() {
      assertThat(
          MessageFormat.format("{0,number,0000}", 1)
      ).isEqualTo("0001");
      assertThat(
          String.format("%04d", 1)
      ).isEqualTo("0001");
    }

    @DisplayName("Objectに変換しても、ダメみたい")
    @Test
    void test_06() {
      assertThatThrownBy(
          () -> MessageFormat.format("{0,number,0000}", (Object)"1")
      ).isInstanceOf(IllegalArgumentException.class);
      assertThatThrownBy(
          () -> MessageFormat.format("{0,number,0000}", "1")
      ).isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  class DateFormatter {
    @Test
    void test_01() {
      Date now = Date.from(Instant.ofEpochSecond(1619358874));
      assertThat(
          MessageFormat.format("{0,date,yyyy年MM月dd日} {0,time,HH時mm分ss秒}", now)
      ).isEqualTo("2021年04月25日 22時54分34秒");
    }

    @Test
    void test_02() {
      Date now = Date.from(Instant.ofEpochSecond(1619358874));
      assertThat(
          MessageFormat.format("{0,date} {0,time}", now)
      ).isEqualTo("2021/04/25 22:54:34");
    }

    @Test
    void test_03() {
      Date now = Date.from(Instant.ofEpochSecond(1619358874));
      MessageFormat mf = new MessageFormat("{0,date} {0,time}");
      assertThat(
          mf.format(new Date[]{now})
      ).isEqualTo("2021/04/25 22:54:34");
    }

    @Test
    void test_04() {
      Date now = Date.from(Instant.ofEpochSecond(1619358874));
      MessageFormat mf = new MessageFormat("{0,date} {0,time}", Locale.ENGLISH);
      assertThat(
          mf.format(new Date[]{now})
      ).isEqualTo("Apr 25, 2021 10:54:34 PM");
    }
  }
}
