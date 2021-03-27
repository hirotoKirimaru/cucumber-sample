package kirimaru.biz.domain.weather;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherDomainServiceTests {
  WeatherDomainService target = new WeatherDomainService(new CsvMapper());


  private final Path path = Path.of("/tmp/2014ebina.csv");

  @BeforeEach
  void setup() throws IOException {
    FileUtils.copyToFile(
        getClass().getResourceAsStream("/kell/2014ebina.csv"),
        path.toFile()
    );
  }

  @AfterEach
  void teardown() throws IOException {
    FileUtils.deleteQuietly(
        path.toFile()
    );
  }

  @Nested
  class WithHeader {

    @Test
    void test_01() throws IOException {
      var weathers = target.readCsc(path);
      assertThat(weathers).hasSize(365);
      assertThat(weathers).contains(
          Weather.builder()
              .date(LocalDate.of(2014, 1, 1))
              .temperatureAverage(BigDecimal.valueOf(7.1))
              .temperatureHigh(BigDecimal.valueOf(15.0))
              .temperatureLow(BigDecimal.valueOf(-2.5))
              .daylightHours(BigDecimal.valueOf(8.5))
              .precipitationAmount(BigDecimal.valueOf(0.0))
              .build()

      );
    }
  }

  @Nested
  class NoHeader {
    private final Path path = Path.of("/tmp/2014ebina2.csv");

    @BeforeEach
    void setup() throws IOException {
      FileUtils.copyToFile(
          getClass().getResourceAsStream("/kell/2014ebina2.csv"),
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
      var weathers = target.readCsvNoHeader(path);
      assertThat(weathers).hasSize(2);
      assertThat(weathers).isEqualTo(
          List.of(
              Weather.builder()
                  .date(LocalDate.of(2014, 1, 1))
                  .temperatureAverage(BigDecimal.valueOf(7.1))
                  .temperatureHigh(BigDecimal.valueOf(15.0))
                  .temperatureLow(BigDecimal.valueOf(-2.5))
                  .daylightHours(BigDecimal.valueOf(8.5))
                  .precipitationAmount(BigDecimal.valueOf(0.0))
                  .build(),
              Weather.builder()
                  .date(LocalDate.of(2014, 1, 8))
                  .temperatureAverage(BigDecimal.valueOf(6.3))
                  .temperatureHigh(BigDecimal.valueOf(14.7))
                  .temperatureLow(BigDecimal.valueOf(-1.0))
                  .daylightHours(BigDecimal.valueOf(3.0))
                  .precipitationAmount(BigDecimal.valueOf(17.0))
                  .build()

              )
      );
    }
  }
}
