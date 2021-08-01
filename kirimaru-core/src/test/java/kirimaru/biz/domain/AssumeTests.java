package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("事前条件(Assume)確認テスト")
class AssumeTests {

  private String driverClassName = "org.postgresql.Driver";

  @Nested
  class AssumeTrue {

    @Test
    void test_01() {
      assumeTrue(driverClassName.contains("oracle"));

      fail();
    }

    @Test
    void test_02() {
      assumeTrue(
          driverClassName.contains("oracle"),
          () ->"このテストはoracle環境では動きません。"
      );

      fail();
    }
  }

  @Nested
  class AssumeFalse {

    @Test
    void test_01() {
      assumeFalse(driverClassName.contains("postgres"));

      fail();
    }

    @Test
    void test_02() {
      assumeFalse(
          driverClassName.contains("postgres"),
          "このテストはoracle環境では動きません。"
      );

      fail();
    }
  }


  @Test
  void test_01() {
    assumingThat(
        driverClassName.contains("postgres"),
        () -> {
          // ここでエラーになったらここで止まる
          assertThat(1).isEqualTo(1);
        }
    );

    // assumingThatはここまで通過する
    // fail();
  }
}