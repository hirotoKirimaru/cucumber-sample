package kirimaru.biz.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import kirimaru.biz.domain.CustomYearMonth;
import kirimaru.biz.domain.date.SystemDateTimeResolver;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class IAm17Impl {
  private final SystemDateTimeResolver systemDateTimeResolver;

  public CustomYearMonth iam17(LocalDate birth) {
    LocalDateTime now = systemDateTimeResolver.now();

    int year = now.getYear() - birth.getYear();
    int month = now.getMonthValue() - birth.getMonthValue();
    int day = now.getDayOfMonth() - birth.getDayOfMonth();

    if (day < 0) {
      month--;
    }

    month += ((year - 17) * 12);
    year = 17;


    return CustomYearMonth.of(year, month);
  }


}
