package kirimaru.biz.service;

import kirimaru.biz.domain.Question;
import kirimaru.biz.repository.QuestionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class QuestionServiceImpl implements QuestionService {
  private final QuestionRepository repository;

  private List<Question> questionList;

  @Override
  public List<Question> selectQuestions(List<Integer> genre, int size) {
    return repository.findQuestions(genre, size);
  }


}
