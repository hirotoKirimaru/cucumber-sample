package com.example.demo.service;

import com.example.demo.domain.Questions;

import java.util.List;

public interface QuestionService {
  List<Questions> selectQuestions(List<Integer> genre, int size);
}
