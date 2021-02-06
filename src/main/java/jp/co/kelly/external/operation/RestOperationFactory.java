package jp.co.kelly.external.operation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class RestOperationFactory {
  private final RestTemplateBuilder restTemplateBuilder;
  private final RestTemplateInterceptor restTemplateInterceptor;

  public RestOperations createRestOperations(ExternalProperties props) {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    converter.setSupportedMediaTypes(List.of(MediaType.TEXT_PLAIN));

    RestTemplate build = restTemplateBuilder
        .setConnectTimeout(Duration.ofSeconds(props.getConnectionTimeout()))
        .setReadTimeout(Duration.ofSeconds(props.getReadTimeout()))
        .interceptors(restTemplateInterceptor)
        .build();
    build.getMessageConverters().add(converter);

    if (log.isInfoEnabled()) {
      log.info("Initialize RestTemplate completed. properties=[{}]", props);
    }

    return build;
  }
}
