package kirimaru.biz.domain.spel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@Disabled("動かない")
class ArithmeticOperatorsTests {

  @Disabled("動かない")
  @Nested
  class Default {
    @Test
    void test_01() {
      ArithmeticOperators target = new ArithmeticOperators();

      assertThat(target.getAdd()).isEqualTo(20);
    }
  }

  @Nested
  @SpringJUnitConfig(ArithmeticOperatorsTests.Config.class)
  class Spring {
    @Autowired
    ArithmeticOperators target;

    @DisplayName("DIしないと値を取得できない")
    @Test
    void test_01() {
//      ArithmeticOperators target = new ArithmeticOperators();

      assertThat(target.getAdd()).isEqualTo(20);
    }
  }

  @ComponentScan(value = {"kirimaru.biz.domain.spel"}, nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
  static class Config {
  }
}
