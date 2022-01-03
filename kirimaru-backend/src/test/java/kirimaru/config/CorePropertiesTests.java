package kirimaru.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({CoreProperties.class})
//@ActiveProfiles("stg")  // ActiveProfileではない
class CorePropertiesTests {
  @Autowired
  CoreProperties coreProperties;

  @Test
  void test_01(){
    assertThat(coreProperties.getAppName()).isEqualTo("Kirimaru");
  }

}
