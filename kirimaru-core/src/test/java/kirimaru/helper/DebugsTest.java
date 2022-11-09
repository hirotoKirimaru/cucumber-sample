package kirimaru.helper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.json.JSONException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class DebugsTest {

  @Nested
  class DebugJson {

    @Data
    @Builder
    public static class Parent {

      private String id;
      private String name;
      private LocalDateTime createdAt;
      private Children children;
    }

    @Data
    @Builder
    private static class Children {

      private List<Child> children;
    }

    @Data
    @Builder
    private static class Child {

      private String id;
      private String name;
    }


    @Test
    void test_01() {
      assertThat(
          Debugs.debugJson("")
      ).isEqualTo("");
    }

    @Test
    void test_02() throws JSONException {
      JSONAssert.assertEquals(
          "{}",
          Debugs.debugJson(
              Parent.builder().build()
          ),
          JSONCompareMode.STRICT
      );
    }


    @Test
    void test_03() throws JSONException {
      // LANGUAGE=JSON
      String expect = """
           {
              "id": "10000",
              "name": "親",
              "children": {
              "children": [
                {
                  "id": "20001"
                },
                {
                  "id": "20002",
                  "name": "2番目"
                }
              ]
            }
          }
          """;
      String actual = Debugs.debugJson(
          Parent.builder()
              .id("10000")
              .name("親")
              .children(Children.builder()
                  .children(List.of(
                      Child.builder()
                          .id("20001")
                          .name(null)
                          .build(),
                      Child.builder()
                          .id("20002")
                          .name("2番目")
                          .build()
                  ))
                  .build())
              .build()
      );
      JSONAssert.assertEquals(
          expect,
          actual,
          JSONCompareMode.STRICT
      );
    }


    @Test
    void test_04() throws JSONException {
      // LANGUAGE=JSON
      String expect = """
           {
              "id": "10000",
              "name": "親",
              "children": {}
          }
          """;
      String actual = Debugs.debugJson(
          Parent.builder()
              .id("10000")
              .name("親")
              .children(Children.builder()
                  .build())
              .build()
      );
      JSONAssert.assertEquals(
          expect,
          actual,
          JSONCompareMode.STRICT
      );
    }


    @Test
    void test_05() throws JSONException {
      // LANGUAGE=JSON
      String expect = """
           {
              "id": "10000",
              "name": "親"
          }
          """;
      String actual = Debugs.debugJson(
          Parent.builder()
              .id("10000")
              .name("親")
              .build()
      );
      JSONAssert.assertEquals(
          expect,
          actual,
          JSONCompareMode.STRICT
      );
    }

    @Test
    void test_06() throws JSONException {
      // LANGUAGE=JSON
      String expect = """
           {
              "id": "10000",
              "name": "親",
              "createdAt": "2020/12/01 02:03:04"
          }
          """;
      String actual = Debugs.debugJson(
          Parent.builder()
              .id("10000")
              .name("親")
              .createdAt(LocalDateTime.of(2020, 12, 1, 2, 3, 4))
              .build()
      );
      JSONAssert.assertEquals(
          expect,
          actual,
          JSONCompareMode.STRICT
      );
    }
  }
}