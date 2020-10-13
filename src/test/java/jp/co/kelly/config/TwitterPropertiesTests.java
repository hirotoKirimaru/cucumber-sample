package jp.co.kelly.config;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(initializers = ConfigFileApplicationContextInitializer.class)
@EnableConfigurationProperties({TwitterProperties.class})
class TwitterPropertiesTests {
  @Autowired
  TwitterProperties properties;

  @SpringJUnitConfig(initializers = ConfigFileApplicationContextInitializer.class)
  @EnableConfigurationProperties({TwitterProperties.class})
  @TestPropertySource(properties = {"external.twitter.protocol=https"})
  @Nested
  class Illegal {
    @Autowired
    TwitterProperties properties;

    @Test
    void test_01() {
      URI url = properties.getUri();
      assertThat(url.toString()).isEqualTo("https://localhost:80/twitter");
    }
  }

  @Test
  void test_02() {
    URI url = properties.getUri();
    assertThat(url.toString()).isEqualTo("http://localhost:80/twitter");
  }
}
