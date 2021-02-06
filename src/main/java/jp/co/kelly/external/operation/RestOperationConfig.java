package jp.co.kelly.external.operation;

import jp.co.kelly.external.zip_cloud.ZipCloudClientProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;


@Configuration
@RequiredArgsConstructor
public class RestOperationConfig {
  private final RestOperationFactory restOperationFactory;

  @Bean
  RestOperations zipCloudRestOperations(ZipCloudClientProperties props) {
    return restOperationFactory.createRestOperations(props);
  }
}
