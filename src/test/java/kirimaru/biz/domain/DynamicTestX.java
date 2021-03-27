package kirimaru.biz.domain;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class DynamicTestX {
  @Disabled
  @TestFactory
  List<DynamicTest> test_01() {
    List<DynamicTest> tests = new ArrayList<>();

    tests.add(dynamicTest("1",
        () -> assertAll(
            () -> assertEquals(1L, 2L)
        )));

    tests.add(dynamicTest("2",
        () -> assertAll(
            () -> assertEquals(3L, 4L)
        )));

    return tests;
  }

  @Disabled
  @Test
  void test_02(){
    // 動かない…
    test_01();
  }

  @TestFactory
  void test_03(){
    // 動かない…
    test_01();
  }


}
