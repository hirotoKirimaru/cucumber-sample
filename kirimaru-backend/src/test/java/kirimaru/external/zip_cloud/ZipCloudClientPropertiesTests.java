package kirimaru.external.zip_cloud;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Disabled("今は意味がない")
@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({ZipCloudClientProperties.class})
class ZipCloudClientPropertiesTests {
  @Autowired
  ZipCloudClientProperties properties;

//  @Test
//  void test_01() {
//    properties.toString();
//  }

  @Test
  void test_02() {
    String url = properties.getUrl();
    assertThat(url).isEqualTo("");
  }
}

