package jp.co.kelly.biz.service;

import jp.co.kelly.biz.domain.Questions;

import java.util.List;

public interface QuestionService {
  List<Questions> selectQuestions(List<Integer> genre, int size);
}
