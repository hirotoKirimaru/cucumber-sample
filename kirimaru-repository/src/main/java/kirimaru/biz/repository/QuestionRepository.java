package kirimaru.biz.repository;

import kirimaru.biz.domain.Question;

import java.util.List;

public interface QuestionRepository {
  List<Question> findQuestions(List<Integer> genre, int size);
}
