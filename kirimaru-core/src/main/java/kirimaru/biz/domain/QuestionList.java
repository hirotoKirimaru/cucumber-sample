package kirimaru.biz.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionList {
  List<Question> value;

  public Map<Integer, List<Question>> groupByGenre() {
    return value.stream()
        .collect(Collectors.groupingBy(
            Question::getGenre
        ));
  }
}
