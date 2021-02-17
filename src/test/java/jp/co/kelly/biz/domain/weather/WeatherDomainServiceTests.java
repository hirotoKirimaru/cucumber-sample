package jp.co.kelly.biz.domain.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeatherDomainServiceTests {
  WeatherDomainService target = new WeatherDomainService(new ObjectMapper());
  @Test
  void test_01() {
    var weathers = target.readCsc();

    assertThat(weathers).isEmpty();
  }
}
