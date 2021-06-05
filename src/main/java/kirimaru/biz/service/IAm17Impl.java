package kirimaru.biz.service;

import kirimaru.biz.domain.Questions;
import kirimaru.biz.domain.date.SystemDateTimeResolver;
import kirimaru.biz.domain.date.SystemDateTimeResolverImpl;
import kirimaru.biz.repository.QuestionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class IAm17Impl {
  private final SystemDateTimeResolver systemDateTimeResolver;

  public String iam17(LocalDateTime birth) {
    return "";
  }


}
