package kirimaru.biz.service;

import java.util.List;
import kirimaru.biz.domain.Question;

public interface QuestionService {
  List<Question> selectQuestions(List<Integer> genre, int size);
}
