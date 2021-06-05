package kirimaru.biz.service;

import kirimaru.biz.domain.Questions;
import kirimaru.biz.domain.date.SystemDateTimeResolver;
import kirimaru.biz.domain.date.SystemDateTimeResolverImpl;
import kirimaru.biz.repository.QuestionRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class IAm17Impl {
  private final SystemDateTimeResolver systemDateTimeResolver;

  public String iam17(LocalDate birth) {
    LocalDateTime now = systemDateTimeResolver.now();
    int year = now.getYear() - birth.getYear();
    int month = now.getMonthValue() - birth.getMonthValue();
    if (birth.getYear() >= 17) {
      month = month + ((year - 17) * 12);
      year = 17;
    } else {
      year = birth.getYear();
    }

    return year + "歳" + month + "ヵ月";
  }


}
