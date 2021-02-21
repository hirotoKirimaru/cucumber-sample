package jp.co.kelly.biz.domain.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
  }
}
