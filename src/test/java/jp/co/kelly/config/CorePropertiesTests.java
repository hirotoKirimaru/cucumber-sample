package jp.co.kelly.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(initializers = ConfigFileApplicationContextInitializer.class)
@EnableConfigurationProperties({CoreProperties.class})
//@ActiveProfiles("stg")  // ActiveProfileではない
class CorePropertiesTests {
  @Autowired
  CoreProperties coreProperties;

  @Test
  void test_01(){
    assertEquals(coreProperties.getAppName(), "HogeHoge");
  }

}
