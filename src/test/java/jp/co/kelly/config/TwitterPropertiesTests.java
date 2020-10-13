package jp.co.kelly.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(initializers = ConfigFileApplicationContextInitializer.class)
@EnableConfigurationProperties({TwitterProperties.class})
class TwitterPropertiesTests {
  @Autowired
  TwitterProperties properties;

  @Disabled("どうやってDIする？")
  @Test
  void test_01(){
    assertThatThrownBy(
        () -> properties.getUrl()
    ).isInstanceOfSatisfying(
        RuntimeException.class,
        (e) -> assertThat(e.getMessage()).isEqualTo("URLじゃないよ！")
    );
  }

  @Test
  void test_02(){
    URL url = properties.getUrl();
    assertThat(url.toExternalForm()).isEqualTo("http://localhost:80/twitter");
  }
}
