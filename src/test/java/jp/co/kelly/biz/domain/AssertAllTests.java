package jp.co.kelly.biz.domain;

import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

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

    assertAll(
      testList
    );

  }

}
