package com.example.demo.biz.service;

import com.example.demo.biz.domain.Questions;
import com.example.demo.biz.repository.QuestionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class QuestionServiceImpl implements QuestionService {
  private final QuestionRepository repository;

  private List<Questions> questionList;

  @Override
  public List<Questions> selectQuestions(List<Integer> genre, int size) {
    return repository.findQuestions(genre, size);
  }


}
