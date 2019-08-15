package com.example.demo.biz.repository;

import com.example.demo.biz.domain.Questions;

import java.util.List;

public interface QuestionRepository {
  List<Questions> findQuestions(List<Integer> genre, int size);
}
