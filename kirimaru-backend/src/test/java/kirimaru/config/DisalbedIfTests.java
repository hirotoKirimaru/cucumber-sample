package kirimaru.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@Disabled("わからぬ")
@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({CoreProperties.class})
//@ActiveProfiles("stg")  // ActiveProfileではない
class DisalbedIfTests {

  @Autowired
  CoreProperties coreProperties;

//  @Value("${app.config.appName}")
//  private String a;

  @Disabled("systemPropertiesではないので、取得できない")
//  @DisabledIf(value = "#{${app.config.appName}.equalsIgnoreCase('kirimaru')}", loadContext = true)
  @DisabledIf(value = "#{${'app.config.appName': 'kirimaru'}.equalsIgnoreCase('kirimaru')}", loadContext = true) // 流石に成功するパターン
//  @DisabledIf(value = "#{#{app.config.appName}.equalsIgnoreCase('kirimaru')}", loadContext = true)
//  @DisabledIf(value = "#{${CoreProperties}.getAppName().equalsIgnoreCase('kirimaru')}", loadContext = true)
  @Test
  void test_01() {
    fail();
  }

  // 初期値を使用する
  @DisabledIf("${app.config.local:true}")
  @Test
  void test_02() {
    fail();
  }

  // loadContextをtrueにしないと、yamlからは読み込めない
  @DisabledIf(value = "${app.config.local}", loadContext = true)
  @Test
  void test_03() {
    fail();
  }

  @DisabledIf("#{systemProperties['os.name'].toLowerCase().contains('win')}")
  @Test
  public void test_04() {
    fail();
  }
}
