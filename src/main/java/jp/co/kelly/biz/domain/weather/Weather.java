package jp.co.kelly.biz.domain.weather;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
  private String date;
  private String temperatureAverage;
  private String temparatureHigh;
  private String temparatureLow;
  private String daylightHours;
  private String precipitationAmount;
//  private LocalDate date;
//  private BigDecimal temperatureAverage;
//  private BigDecimal temparatureHigh;
//  private BigDecimal temparatureLow;
//  private BigDecimal daylightHours;
//  private BigDecimal precipitationAmount;
}
