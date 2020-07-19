package jp.co.kelly.biz.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 申込 -> List<明細> -> List<返品履歴>の時に、
 * List<返品履歴>をフラットに取得したい
 */
class NestedList {

  @DisplayName("NestのNestのNest…をうまく処理する方法")
  @Nested
  public class FlatHistory {
    @Test
    void test_01() {
      Entry entry =
          Entry.builder()
              .build();

      List<ReturnHistory> actual = entry.flatHistory();

      assertThat(actual).isEmpty();
    }

    @Test
    void test_02() {
      Entry entry =
          Entry.builder()
              .detail(
                  List.of(
                      Detail.builder()
                          .returnHistory(List.of())
                          .build(),
                      Detail.builder()
                          .build()
                  )
              )
              .build();

      List<ReturnHistory> actual = entry.flatHistory();

      assertThat(actual).hasSize(1);
    }

    @Test
    void test_03() {
      Entry entry =
          Entry.builder()
              .detail(
                  List.of(
                      Detail.builder()
                          .returnHistory(List.of(
                              ReturnHistory.builder()
                                  .id("1")
                                  .date(LocalDateTime.of(2015, 1, 1, 1, 1, 1))
                                  .build(),
                              ReturnHistory.builder()
                                  .id("2")
                                  .date(LocalDateTime.of(2016, 1, 1, 1, 1, 1))
                                  .build()
                          ))
                          .build(),
                      Detail.builder()
                          .returnHistory(List.of(
                              ReturnHistory.builder()
                                  .id("3")
                                  .date(LocalDateTime.of(2017, 1, 1, 1, 1, 1))
                                  .build()
                          ))
                          .build(),
                      Detail.builder()
                          .build()
                  )
              )
              .build();

      List<ReturnHistory> actual = entry.flatHistory();

      assertThat(actual).hasSize(3);
    }
  }


}
