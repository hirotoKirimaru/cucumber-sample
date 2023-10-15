package kirimaru.biz.repository;

import java.util.List;
import kirimaru.biz.domain.Question;

public interface QuestionRepository {
  List<Question> findQuestions(List<Integer> genre, int size);
}
