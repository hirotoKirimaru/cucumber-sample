package kirimaru.biz.domain;

import kirimaru.helper.anotation.DBSetup;
import kirimaru.helper.listeners.DBSetupListener;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

//@SpringBootTest
@SpringJUnitConfig(AnotationTests.Config.class)
//@ExtendWith(SpringExtension.class)
@TestExecutionListeners({
    DBSetupListener.class
})
class AnotationTests {
  @ComponentScan({"kirimaru.biz.domain.hogehoge"})
  public static class Config {
  }


  @DBSetup(path = "ああああ")
  @Test
  void test_01() {
    System.out.println("キリン");
  }

  @Test
  void test_02() {
    System.out.println("コアラ");
  }
}
