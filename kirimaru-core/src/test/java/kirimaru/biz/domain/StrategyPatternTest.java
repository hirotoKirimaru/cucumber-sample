package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StrategyPatternTest {

  @Nested
  class groupByGenre {

    @Test
    void test_01() {
      var target = new QuestionList(
          List.of(
              Question.builder().build()
          )
      );

      assertThat(
          target.groupByGenre()
      ).isEqualTo(Map.of(0, List.of(Question.builder().build())));
    }

    @Test
    void test_02() {
      var target = new QuestionList(
          List.of(
              Question.builder().genre(0).build(),
              Question.builder().genre(1).build(),
              Question.builder().genre(1).build(),
              Question.builder().genre(2).build(),
              Question.builder().genre(4).build()
          )
      );

      assertThat(
          target.groupByGenre()
      ).containsAllEntriesOf(
          Map.of(
              0, List.of(Question.builder().genre(0).build()),
              1, List.of(Question.builder().genre(1).build(), Question.builder().genre(1).build()),
              2, List.of(Question.builder().genre(2).build()),
              4, List.of(Question.builder().genre(4).build())
          )
      );
    }
  }

}