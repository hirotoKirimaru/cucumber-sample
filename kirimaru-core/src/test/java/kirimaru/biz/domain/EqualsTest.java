package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class EqualsTest {
  @Test
  void test_01() {
    boolean param1 = true;
    Boolean param2 = null;
    try {
      var actual = param1 == param2;
      Assert.fail();
    } catch (NullPointerException e) {
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  void test_02() {
    boolean param1 = true;
    Boolean param2 = null;
    assertThat(
        Objects.equals(param1, param2)
    ).isEqualTo(false);
  }

  @Test
  void test_03() {
    boolean param1 = true;
    Boolean param2 = true;
    assertThat(
        Objects.equals(param1, param2)
    ).isEqualTo(true);
  }

  @Test
  void test_04() {
    boolean param1 = true;
    Boolean param2 = false;
    assertThat(
        Objects.equals(param1, param2)
    ).isEqualTo(false);
  }

  @AllArgsConstructor
  static class Nested {
    public Boolean bool;
  }

  @Test
  void test_05() {
    boolean param1 = true;
    Nested param2 = null;
    try {
      Objects.equals(param1, param2.bool);
      Assert.fail();
    } catch (NullPointerException e) {
    } catch (Exception e) {
      Assert.fail();
    }
  }

  @Test
  void test_06() {
    assertThat(
        BigDecimal.ONE.equals(null)
    ).isEqualTo(false);
  }
}
