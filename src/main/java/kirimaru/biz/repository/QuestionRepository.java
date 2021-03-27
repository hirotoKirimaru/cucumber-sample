package kirimaru.biz.repository;

import kirimaru.biz.domain.Questions;

import java.util.List;

public interface QuestionRepository {
  List<Questions> findQuestions(List<Integer> genre, int size);
}
