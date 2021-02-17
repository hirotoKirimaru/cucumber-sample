package jp.co.kelly.biz.domain.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
public class WeatherDomainService {
  private final ObjectMapper objectMapper;
  public List<Weather> readCsc() {

    return List.of();
  }
}
