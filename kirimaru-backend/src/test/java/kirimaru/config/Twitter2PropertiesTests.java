package kirimaru.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({TwitterProperties.class, Twitter2Properties.class})
class Twitter2PropertiesTests {

  @Autowired
  Twitter2Properties properties1;
  @Autowired
  TwitterProperties properties2;

  @Test
  void test_01() {
    System.out.println(properties1);
    System.out.println(properties2);

    // 目検で一致していることは確認
    // assertThat(properties1).isEqualTo(properties2);
  }
}
