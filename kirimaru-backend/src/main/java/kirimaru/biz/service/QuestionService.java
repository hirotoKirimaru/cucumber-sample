package kirimaru.biz.service;

import kirimaru.biz.domain.Questions;

import java.util.List;

public interface QuestionService {
  List<Questions> selectQuestions(List<Integer> genre, int size);
}
