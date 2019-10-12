package jp.co.kelly.biz.service;

import jp.co.kelly.biz.domain.Questions;
import jp.co.kelly.biz.repository.QuestionRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuestionServiceImplTest {
  private QuestionRepository repository;
  private QuestionService target;

  private SoftAssertions softly = new SoftAssertions();

  QuestionServiceImplTest() {
    repository = mock(QuestionRepository.class);
    target = new QuestionServiceImpl(repository);
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
    List<Questions> questionsList =
        Arrays.asList(
            Questions.builder().build(),
            Questions.builder().build(),
            Questions.builder().build(),
            Questions.builder().build(),
            Questions.builder().build(),
            Questions.builder().build(),
            Questions.builder().build(),
            Questions.builder().build(),
            Questions.builder().build(),
            Questions.builder().build()
        );

    when(repository.findQuestions(genreList, 10)).thenReturn(questionsList);

    List<Questions> actual = target.selectQuestions(genreList, 10);

    softly.assertThat(actual).hasSize(10);
  }
}