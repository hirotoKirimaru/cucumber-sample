package com.example.demo.biz.service;

import com.example.demo.biz.domain.Questions;

import java.util.List;

public interface QuestionService {
  List<Questions> selectQuestions(List<Integer> genre, int size);
}
