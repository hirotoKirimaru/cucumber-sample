package kirimaru.biz.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import kirimaru.biz.domain.Question;
import kirimaru.biz.domain.constant.CodeConstant;
import kirimaru.biz.mapper.QuestionMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class QuestionRepositoryImplTest {
  @Mock
  private QuestionMapper mapper;
  private QuestionRepository target;

  @BeforeEach
  void setup() {
    target = new QuestionRepositoryImpl(mapper);
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
    List<Question> actual = target.findQuestions(genreList, 10);

    SoftAssertions softly = new SoftAssertions();

    softly.assertThat(actual).hasSize(10);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), CodeConstant.Questions.Genre.JAVA.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), CodeConstant.Questions.Genre.JAVASCRIPT.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), CodeConstant.Questions.Genre.RUBY.getType())).collect(Collectors.toList())).hasSizeBetween(3, 4);
    softly.assertThat(actual.stream().filter(e -> Objects.equals(e.getGenre(), CodeConstant.Questions.Genre.PYTHON.getType())).collect(Collectors.toList())).hasSize(0);

    softly.assertAll();
  }
}
