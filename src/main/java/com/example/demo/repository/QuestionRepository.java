package com.example.demo.repository;

import com.example.demo.domain.Questions;

import java.util.List;

public interface QuestionRepository {
  List<Questions> findQuestions(List<Integer> genre, int size);
}
