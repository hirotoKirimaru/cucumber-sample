package kirimaru.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({FacebookProperties.class})
class FacebookPropertiesTests {
  @Autowired
  FacebookProperties properties;

  @Test
  void test_01() {
    URI url = properties.getUri();
    assertThat(url.toString()).isEqualTo("http://localhost:10080/facebook");
  }

  @Test
  void AutowiredしないときはPostConstructが呼ばれない() {
    new FacebookProperties();

    // 起動ログを目視で確認する

  }
}
