package kirimaru.biz.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * AssertAllをしゃぶりつくるためのテスト
 */
class AssertAllTests {

  @DisplayName("アサーションルーレットを忌避するためにしゃぶり尽くす")
  @Test
  void test_01() {
    List<Executable> testList = new ArrayList<>();

    Executable exec = () -> assertThat(true).as("trueになるやつ").isTrue();
    Executable exec2 = () -> assertThat(true).as("falseになるやつ").isFalse();

    testList.add(exec);
//    testList.add(exec2);

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(true).isTrue();
//    softly.assertThat(true).isFalse();

    Executable exec3 = () -> softly.assertAll();

    testList.add(exec3);

    assertAll(
      testList
    );

  }

}
