package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

class RequestDateTests {

  @Test
  void test_01() {
    LocalDate localDate = LocalDate.of(2022, 5, 7);
    String string = "20220507";
    assertThat(
        RequestDate.of(string)
    ).isEqualTo(new RequestDate(string, localDate));
  }

  @Test
  void test_02() {
    LocalDate localDate = LocalDate.of(2022, 5, 7);
    String string = "20220507";
    RequestDate requestDate = new RequestDate(string, localDate);

    SoftAssertions softly = new SoftAssertions();

    softly.assertThat(requestDate.string()).isEqualTo(string);
    softly.assertThat(requestDate.localDate()).isEqualTo(localDate);

    softly.assertAll();
  }

  @Test
  void test_03() {
    LocalDate localDate = LocalDate.of(2022, 5, 7);
    String string = "20220507";
    RequestDate requestDate = new RequestDate(string, localDate);
    String string2 = "20200101";
    LocalDate localDate2 = LocalDate.of(2020,1,1);
    requestDate.string(string2).localDate(localDate2);

    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(requestDate.string()).isEqualTo(string2);
    softly.assertThat(requestDate.localDate()).isEqualTo(localDate2);

    softly.assertAll();
  }
}