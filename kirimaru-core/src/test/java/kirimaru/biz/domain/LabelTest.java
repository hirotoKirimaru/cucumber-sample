package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LabelTest {


  @Test
  void test_01() {
    String actual = "";

    END:
    for (String s : List.of("111", "222", "333")) {
      actual += s;
      for (String s1 : List.of("11", "22", "33")) {
        actual += s1;
        if (s1.equals("22")) {
          break END;
        }
      }
    }
    assertThat(actual).isEqualTo("1111122");
  }


  @Test
  @Disabled("意味がない")
  void test_02() {
    String actual = "";

    END:
    for (String s : List.of("111", "222", "333")) {
      actual += s;
      START:
      for (String s1 : List.of("11", "22", "33")) {
        actual += s1;
        if (s1.equals("22")) {
          break START;
        }
      }
    }

    TWO_LOOP:
    {

    }

    assertThat(actual).isEqualTo("1111122");
  }
}
