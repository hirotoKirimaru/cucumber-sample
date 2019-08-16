package jp.co.kelly.biz.repository;

import jp.co.kelly.biz.domain.Questions;
import jp.co.kelly.biz.mapper.QuestionMapper;
import jp.co.kelly.biz.domain.CodeConstant;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        CodeConstant.Questions.Genre.JAVA.getType(),
        CodeConstant.Questions.Genre.JAVASCRIPT.getType(),
        CodeConstant.Questions.Genre.RUBY.getType()
    );


    List<Questions> actual = target.findQuestions(genreList, 10);

    softly.assertThat(actual).hasSize(10);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), CodeConstant.Questions.Genre.JAVA.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), CodeConstant.Questions.Genre.JAVASCRIPT.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), CodeConstant.Questions.Genre.RUBY.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), CodeConstant.Questions.Genre.PYTHON.getType())).collect(Collectors.toList())).hasSize(0);

  }
}