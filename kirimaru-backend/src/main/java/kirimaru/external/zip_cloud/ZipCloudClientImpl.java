package kirimaru.external.zip_cloud;

import kirimaru.external.zip_cloud.dto.ZipCloudDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.HashMap;

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
