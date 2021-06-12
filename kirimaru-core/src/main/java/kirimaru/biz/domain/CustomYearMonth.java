package kirimaru.biz.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomYearMonth {
  private int year;
  private int month;


  @Override
  public String toString() {
    return new StringBuilder().append(year).append("歳").append(month).append("ヵ月").toString();
  }

  public static CustomYearMonth of(int year, int month) {
    return new CustomYearMonth(year, month);
  }
}
