package com.example.demo.biz.repository;

import com.example.demo.biz.domain.Questions;
import com.example.demo.biz.mapper.QuestionMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

class QuestionRepositoryImplTest {
  private QuestionMapper mapper;
  private QuestionRepository target;

  private SoftAssertions softly = new SoftAssertions();

  QuestionRepositoryImplTest() {
    mapper = mock(QuestionMapper.class);
    target = new QuestionRepositoryImpl(mapper);
  }

  @BeforeEach
  void setup() {
    softly = new SoftAssertions();
  }

  @AfterEach
  void tearDown() {
    softly.assertAll();
  }

  @Test
  void test_01() {
    List<Integer> genreList = Arrays.asList(1, 2, 3, 4);


    List<Questions> actual = target.findQuestions(genreList, 10);

    softly.assertThat(actual).hasSize(10);
  }
}