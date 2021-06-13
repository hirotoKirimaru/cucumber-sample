package kirimaru.biz.mapper.helper;

import kirimaru.biz.domain.date.SystemDateTimeResolver;
import kirimaru.helper.ApplicationContextAccessorConfig;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static kirimaru.helper.ApplicationContextAccessor.getBean;
import static org.mockito.Mockito.when;

@TestConfiguration
@Import(ApplicationContextAccessorConfig.class)
public class MapperUnitTestConfig {

  @Bean
  SystemDateTimeResolver systemDateTimeResolver(){
    SystemDateTimeResolver resolver = Mockito.mock(SystemDateTimeResolver.class);
    when(resolver.now()).thenReturn(CommonSetup.now);
    return resolver;
  }

  @Bean
  MapperProperties mapperProperties(){
    MapperProperties properties = new MapperProperties();

    return properties;
  }

}
