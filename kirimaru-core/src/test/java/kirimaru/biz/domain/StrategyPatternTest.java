package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class StrategyPatternTest {

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
}