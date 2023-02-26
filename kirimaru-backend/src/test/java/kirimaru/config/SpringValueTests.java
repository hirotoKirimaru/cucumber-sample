package kirimaru.config;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(SpringValueTests.Config.class)
@TestPropertySource(properties = {"app.config.appName=testApplication"})
class SpringValueTests {

  @Value("${app.config.appName:defaultName}")
  private String appName;
  @Value("${app.config.appName2:defaultName}")
  private String appName2;
  @Value("${app.config.appName3:null}")
  private String appName3;
  @Value("${app.config.appName3:#{null}}")
  private String appName4;

  @ComponentScan({"kirimaru.biz.domain.hogehoge"})
  public static class Config {

  }

  @Test
  void test_01() {
    SoftAssertions softly = new SoftAssertions();

    softly.assertThat(appName).isEqualTo("testApplication");
    softly.assertThat(appName2).isEqualTo("defaultName");
    softly.assertThat(appName3).isEqualTo("null");
    softly.assertThat(appName4).isNull();

    softly.assertAll();
  }

}
