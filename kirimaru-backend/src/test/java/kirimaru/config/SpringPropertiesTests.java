package kirimaru.config;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({OsProperties.class})
class SpringPropertiesTests {
  @Autowired
  private OsProperties target;

  @Test
  @Disabled
  @EnabledOnOs(OS.WINDOWS)
  void test_01() {
    SoftAssertions softly = new SoftAssertions();

    softly.assertThat(target.getOs()).isEqualTo("Windows_NT");

    softly.assertAll();
  }

}
