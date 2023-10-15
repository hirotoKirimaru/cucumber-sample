package kirimaru.biz.repository;

import java.util.Arrays;
import java.util.List;
import kirimaru.biz.domain.Question;
import kirimaru.biz.domain.constant.CodeConstant;
import kirimaru.biz.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {
  private final QuestionMapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Question> findQuestions(List<Integer> genre, int size) {
    List<Question> questionList =
        Arrays.asList(
            Question.builder().genre(CodeConstant.Questions.Genre.JAVA.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.JAVA.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.JAVA.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.JAVASCRIPT.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.JAVASCRIPT.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.JAVASCRIPT.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.RUBY.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.RUBY.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.RUBY.getType()).build(),
            Question.builder().genre(CodeConstant.Questions.Genre.RUBY.getType()).build()
        );
    mapper.isis();

    return questionList;
  }
}
