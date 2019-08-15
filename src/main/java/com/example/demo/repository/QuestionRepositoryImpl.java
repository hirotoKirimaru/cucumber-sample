package com.example.demo.repository;

import com.example.demo.domain.Questions;
import com.example.demo.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {
  private final QuestionMapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Questions> findQuestions(List<Integer> genre, int size) {
    return null;
  }
}
