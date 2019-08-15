package com.example.demo.biz.repository;

import com.example.demo.biz.domain.Questions;
import com.example.demo.biz.mapper.QuestionMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.demo.biz.domain.CodeConstant.Questions.Genre.*;
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

  @DisplayName("" +
      "取得した問題数がパラメータと同じであること。" +
      "取得した問題がパラメータのジャンルと一致していること" +
      "取得した場合は ジャンル単位で取得した問題数 にばらつきが無いこと" +
      "パラメータで指定していないジャンルは取得していないこと")
  @Test
  void test_01() {
    List<Integer> genreList = Arrays.asList(
        JAVA.getType(),
        JAVASCRIPT.getType(),
        RUBY.getType()
    );


    List<Questions> actual = target.findQuestions(genreList, 10);

    softly.assertThat(actual).hasSize(10);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), JAVA.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), JAVASCRIPT.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), RUBY.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), PYTHON.getType())).collect(Collectors.toList())).hasSize(0);

  }
}