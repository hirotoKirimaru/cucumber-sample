package jp.co.kelly.biz.domain.weather;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
@AllArgsConstructor
//@NoArgsConstructor
public class Weather {
  private LocalDate date;
  private BigDecimal temperatureAverage;
  private BigDecimal temparatureHigh;
  private BigDecimal temparatureLow;
  private BigDecimal daylightHours;
  private BigDecimal precipitationAmount;
}
