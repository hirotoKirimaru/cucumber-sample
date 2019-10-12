package jp.co.kelly.biz.repository;

import jp.co.kelly.biz.domain.CodeConstant;
import jp.co.kelly.biz.domain.Questions;
import jp.co.kelly.biz.mapper.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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
    List<Questions> questionsList =
        Arrays.asList(
            Questions.builder().genre(CodeConstant.Questions.Genre.JAVA.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.JAVA.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.JAVA.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.JAVASCRIPT.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.JAVASCRIPT.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.JAVASCRIPT.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.RUBY.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.RUBY.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.RUBY.getType()).build(),
            Questions.builder().genre(CodeConstant.Questions.Genre.RUBY.getType()).build()
        );
    mapper.isis();

    return questionsList;
  }
}
