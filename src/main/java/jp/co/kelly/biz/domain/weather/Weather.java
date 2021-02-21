package jp.co.kelly.biz.domain.weather;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
    "date",
    "temperatureAverage",
    "temperatureHigh",
    "temperatureLow",
    "daylightHours",
    "precipitationAmount"
})
public class Weather {
  @JsonProperty("date")
  private LocalDate date;
  @JsonProperty("temperatureAverage")
  private BigDecimal temperatureAverage;
  @JsonProperty("temperatureHigh")
  private BigDecimal temperatureHigh;
  @JsonProperty("temperatureLow")
  private BigDecimal temperatureLow;
  @JsonProperty("daylightHours")
  private BigDecimal daylightHours;
  @JsonProperty("precipitationAmount")
  private BigDecimal precipitationAmount;
}
