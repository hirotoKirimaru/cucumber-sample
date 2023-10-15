package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@Disabled("3回リトライを常に実行するとウザいので、やめる。辞めるとテストが通らなくなるので、テスト自体をDisabledにする")
class RetryTests {
  static Random random = new Random(1);

  @Disabled
  @CsvSource({
      "1",
      "2",
      "3",
      "4"
  })
  @ParameterizedTest
  void test_01() {
   assertThat(random.nextInt()).isEqualTo(1018954901);
  }

  @Test
  void test_02() {
    assertThat(random.nextBoolean()).isEqualTo(true);
  }

  @Test
  void test_03() {
    assertThat(random.nextBoolean()).isEqualTo(true);
  }

  @Test
  void test_04() {
    assertThat(random.nextBoolean()).isEqualTo(true);
  }

//  @Test
//  void test_05() {
//    assertThat(random.nextBoolean()).isEqualTo(true);
//  }
//
//  @Test
//  void test_06() {
//    assertThat(random.nextBoolean()).isEqualTo(true);
//  }
//
//  @Test
//  void test_07() {
//    assertThat(random.nextBoolean()).isEqualTo(true);
//  }
//
//  @Test
//  void test_08() {
//    assertThat(random.nextBoolean()).isEqualTo(true);
//  }
//
//  @Test
//  void test_09() {
//    assertThat(random.nextBoolean()).isEqualTo(true);
//  }
//
//  @Test
//  void test_10() {
//    assertThat(random.nextBoolean()).isEqualTo(true);
//  }

  @Test
  void test_01_02() {
    assertThat(1).isEqualTo(1);
  }
}
