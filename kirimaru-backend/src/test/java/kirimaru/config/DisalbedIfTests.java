package kirimaru.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@Disabled("わからぬ")
@SpringJUnitConfig(initializers = ConfigDataApplicationContextInitializer.class)
@EnableConfigurationProperties({CoreProperties.class})
// @DisabledIfSystemProperty() // こんなのもあるんか
//@ActiveProfiles("stg")  // ActiveProfileではない
class DisalbedIfTests {

  @Autowired
  CoreProperties coreProperties;

//  @Value("${app.config.appName}")
//  private String a;

  // どうも、YAMLの設定値を読み込むことは難しい模様
//  @Disabled("systemPropertiesではないので、取得できない")
//  @DisabledIf(value = "#{${app.config.appName}.equalsIgnoreCase('kirimaru')}", loadContext = true)
  @DisabledIf(value = "#{${'app.config.appName': 'kirimaru'}.equalsIgnoreCase('kirimaru')}", loadContext = true) // 流石に成功するパターン
//  @DisabledIf(value = "#{${app.config.appName}[0].equalsIgnoreCase('kirimaru')}", loadContext = true)
//  @DisabledIf(value = "#{${CoreProperties}.getAppName().equalsIgnoreCase('kirimaru')}", loadContext = true)
  @Test
  void test_01() {
    fail();
  }

  @DisabledIf(value = "#{environment.getProperty('app.config.appName').equalsIgnoreCase('kirimaru')}", loadContext = true) // 流石に成功するパターン
  @Test
  void test_01_01() {
    fail();
  }

  @DisabledIf(value = "#{environment.getProperty('app.config.appName').equalsIgnoreCase('kirimatsu')}", loadContext = true) // 流石に成功するパターン
  @Test
  void test_01_02() {
    // 何も起きないこと
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

  // CIが落ちる

//  @DisabledIf("#{systemProperties['os.name'].toLowerCase().contains('win')}")
//  @Test
//  public void test_04() {
//    fail();
//  }
}
