package jp.co.kelly.biz.repository;

import jp.co.kelly.biz.domain.Questions;

import java.util.List;

public interface QuestionRepository {
  List<Questions> findQuestions(List<Integer> genre, int size);
}
