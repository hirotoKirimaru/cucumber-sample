package kirimaru.biz.domain;

import groovy.util.logging.Slf4j;
import kirimaru.annotation.OptionalTest;
import org.json.JSONException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

@Slf4j
class OptionalTestTest {

  @Nested
  class test {

    @Test
    void test_01() {
      assert 1 == 1;
    }

    @OptionalTest
    void test_02() {
      System.out.println("うごいてしまっている！");
      assert 1 == 1;
    }
  }
}