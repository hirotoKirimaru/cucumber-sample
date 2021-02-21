package jp.co.kelly.biz.domain.weather;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherDomainServiceTests {
  WeatherDomainService target = new WeatherDomainService(new CsvMapper());


  private final Path path = Path.of("/tmp/2014ebina.csv");

  @BeforeEach
  void setup() throws IOException {
    FileUtils.copyToFile(
        getClass().getResourceAsStream("/jp/co/kelly/2014ebina.csv"),
        path.toFile()
    );
  }

  @AfterEach
  void teardown() throws IOException {
    FileUtils.deleteQuietly(
        path.toFile()
    );
  }

  @Test
  void test_01() throws IOException {
    var weathers = target.readCsc(path);
    assertThat(weathers).hasSize(365);
    assertThat(weathers).contains(
        Weather.builder()
            .date(LocalDate.of(2014, 1,1))
            .temperatureAverage(BigDecimal.valueOf(7.1))
            .temperatureHigh(BigDecimal.valueOf(15.0))
            .temperatureLow(BigDecimal.valueOf(-2.5))
            .daylightHours(BigDecimal.valueOf(8.5))
            .precipitationAmount(BigDecimal.valueOf(0.0))
            .build()

    );
  }
}