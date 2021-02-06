package jp.co.kelly.external.zip_cloud;

import jp.co.kelly.external.zip_cloud.dto.ZipCloudDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ZipCloudClientImpl implements ZipCloudClient {
  private final ZipCloudClientProperties props;

  @Override
  public ZipCloudDto getAddressByZipcode(String zipCode) {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("zipcode", zipCode);

    WebClient client = WebClient.builder()
//        .baseUrl(props.getUrl())
        .baseUrl("http://localhost:8080")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//        .defaultUriVariables(hashMap)
        .build();

    WebClient.ResponseSpec retrieve = client.get()
        .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
        .acceptCharset(StandardCharsets.UTF_8)
        .ifNoneMatch("*")
        .ifModifiedSince(ZonedDateTime.now())
        .retrieve();

    return retrieve.bodyToMono(ZipCloudDto.class).block();
  }
}
