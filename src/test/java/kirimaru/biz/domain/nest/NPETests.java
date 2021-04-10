package kirimaru.biz.domain.nest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class NPETests {

  @Test
  void test_01() {
    Integer a = null;

    try {
      boolean hoge = null == a;
//    } catch (NullPointerException e) {

//      System.out.println("エラー発生");

    } catch (Exception e) {
      fail("失敗");
    }
  }

  @Test
  void test_02() {
    Integer a = null;
    try {
      boolean hoge = a == null;
//    } catch (NullPointerException e) {

//      System.out.println("エラー発生");
    } catch (Exception e) {
      fail("失敗");
    }
  }

  @Test
  void test_03() {
    String a = null;
    try {
      boolean hoge = a == null;
    } catch (Exception e) {
      fail("失敗");
    }
  }

  @Test
  void test_04() {
    Integer a = null;
    try {
      boolean hoge = Objects.isNull(a);
    } catch (Exception e) {
      fail("失敗");
    }
  }
}
