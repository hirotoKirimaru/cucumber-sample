package kirimaru.biz.domain;

import java.time.LocalDateTime;
import java.time.Year;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class FiscalYear {
  private final String value;

  public FiscalYear(String value) {
    if (value.length() != 4) {
      throw new RuntimeException("4桁以外");
    }
    this.value = value;
  }

  public String toShortYear() {
    return value.substring(2);
  }

  public Year toYear() {
    return Year.parse(value);
  }

  public LocalDateTime toLocalDateTime() {
    return LocalDateTime.of(Integer.parseInt(value), 1, 1, 0, 0);
  }
}
