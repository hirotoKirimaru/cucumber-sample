package kirimaru.biz.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

      Assertions.assertThat(actual).isEmpty();
    }

    @Test
    void test_02() {
      ReturnHistory expect1 = ReturnHistory.builder()
          .id("1")
          .date(LocalDateTime.of(2015, 1, 1, 1, 1, 1))
          .build();

      Entry entry =
          Entry.builder()
              .detail(
                  List.of(
                      Detail.builder()
                          .returnHistory(List.of(
                              expect1
                          ))
                          .build(),
                      Detail.builder()
                          .build()
                  )
              )
              .build();

      List<ReturnHistory> actual = entry.flatHistory();

      Assertions.assertThat(actual).hasSize(1);
      Assertions.assertThat(actual).containsExactlyInAnyOrder(
          expect1
      );
    }

    @Test
    void test_03() {
      ReturnHistory expect1 = ReturnHistory.builder()
          .id("1")
          .date(LocalDateTime.of(2015, 1, 1, 1, 1, 1))
          .build();
      ReturnHistory expect2 = ReturnHistory.builder()
          .id("2")
          .date(LocalDateTime.of(2016, 1, 1, 1, 1, 1))
          .build();
      ReturnHistory expect3 = ReturnHistory.builder()
          .id("3")
          .date(LocalDateTime.of(2017, 1, 1, 1, 1, 1))
          .build();

      Entry entry =
          Entry.builder()
              .detail(
                  List.of(
                      Detail.builder()
                          .returnHistory(List.of(
                              expect1,
                              expect2
                          ))
                          .build(),
                      Detail.builder()
                          .returnHistory(List.of(
                              expect3
                          ))
                          .build(),
                      Detail.builder()
                          .build()
                  )
              )
              .build();

      List<ReturnHistory> actual = entry.flatHistory();

      Assertions.assertThat(actual).hasSize(3);
      Assertions.assertThat(actual).containsExactlyInAnyOrder(
          expect1, expect2, expect3
      );
    }
  }


}
