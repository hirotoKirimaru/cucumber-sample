package jp.co.kelly.external.zip_cloud;

import jp.co.kelly.external.zip_cloud.dto.ZipCloudDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Component
public class ZipCloudClientImpl implements ZipCloudClient {
  private final ZipCloudClientProperties props;
  private final RestOperations restOperations;

  public ZipCloudClientImpl(
      ZipCloudClientProperties props,
      @Qualifier("zipCloudRestOperations")
          RestOperations restOperations
  ) {
    this.props = props;
    this.restOperations = restOperations;
  }


  @Override
  public ZipCloudDto getAddressByZipcode(String zipCode) {
    HashMap<String, String> hashMap = new HashMap<>();
    hashMap.put("zipcode", zipCode);

    ResponseEntity<ZipCloudDto> responseEntity = restOperations.getForEntity(props.getUrl(hashMap), ZipCloudDto.class);
    return responseEntity.getBody();
  }
}
