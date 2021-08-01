package kirimaru.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@Disabled("わからぬ")
@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({CoreProperties.class})
//@ActiveProfiles("stg")  // ActiveProfileではない
class DisalbedIfTests {

  @Autowired
  CoreProperties coreProperties;

  @DisabledIf("#{systemProperties['app.config.appName'].toLowerCase().contains('kirimaru')}")
  @Test
  void test_01() {
    fail();
  }

  @DisabledIf("${app.config.local}")
  @Test
  void test_02() {
    fail();
  }
}
