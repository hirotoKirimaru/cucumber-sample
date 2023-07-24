package kirimaru.biz.domain;

import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@TestPropertySource(properties = {"domain=https"})
class StaticSpringValue {

  private static String actual;
  @Value("${domain}")
  private static String actual2;
  private static String actual3;

  private final String EXPECTED = "https";

  @Autowired
  StaticSpringValueBean bean;

  public StaticSpringValue(@Value("${domain}") String actual,
      @Value("#{TestBean.actual}") String actual3
  ) {
    StaticSpringValue.actual = actual;
    StaticSpringValue.actual3 = actual3;
  }


// voidで引数なしじゃないとダメ
//  @PostConstruct
//  public void after() {
//  }

  @Test
  @DisplayName("コンストラクタなら値を取得できる")
  void test_01() {
    assertThat(actual).isEqualTo(EXPECTED);
  }

  @Test
  @DisplayName("そのままは無理")
  void test_02() {
    assertThat(actual2).isNull();
  }

  @Test
  @DisplayName("beanに登録して取得")
  void test_03() {
    assertThat(bean.getActual()).isEqualTo(EXPECTED);
  }

  @Test
  @DisplayName("beanに登録して、それをコンストラクタで取得")
  void test_04() {
    assertThat(actual3).isEqualTo(EXPECTED);
  }

  @Data
  @TestConfiguration(value = "TestBean")
  public static class StaticSpringValueBean {
    @Value("${domain}")
    private String actual;
  }
}
