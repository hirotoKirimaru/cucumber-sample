package jp.co.kelly.biz.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CloneTests {

  @Nested
  class Serialization {

    @Test
    public void データをコピーする() {
      ReturnHistory expect = new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1));

      ReturnHistory actual = SerializationUtils.clone(expect);

      assertThat(actual).isEqualTo(expect);
    }

    @Test
    public void serializableのデータはコピーできない() {
      ReturnHistory expect = new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1), new Gorilla("1"), null);

      try {
        SerializationUtils.clone(expect);
      } catch (SerializationException e) {
        // 目的通りのエラーが出ること
      } catch (Exception e) {
        Assertions.fail();
      }

    }

    @Test
    public void データをコピーする3() {
      ReturnHistory expect = new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1),
          null,
          new Animal("999", LocalDateTime.MIN));

      ReturnHistory actual = SerializationUtils.clone(expect);

      System.out.println("====================");
      System.out.println(expect);
      System.out.println("====================");
      System.out.println(actual);

      assertThat(actual).isEqualTo(expect);
    }


    @Test
    public void リストデータをコピーする() {
      Detail expect = new Detail(
          List.of(
              new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1)),
              new ReturnHistory("200", LocalDateTime.of(2020, 10, 1, 1, 1))
          )
      );

      Detail actual = SerializationUtils.clone(expect);

      assertThat(actual).isEqualTo(expect);
    }


    @Nested
    class Fail {
      @Test
      public void actualの値も変わってしまう() {
        ReturnHistory expect = new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1));

        ReturnHistory actual = expect;

        expect.setId("200");

        assertThat(actual).isEqualTo(expect);
      }

      @Disabled("確実に失敗するので")
      @Test
      public void expectの中身だけしか変わらない() {
        ReturnHistory expect = new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1));

        ReturnHistory actual = SerializationUtils.clone(expect);

        expect.setId("200");

        assertThat(actual).isEqualTo(expect);
      }

      @Test
      public void リストデータをコピーする() {
        Detail expect = new Detail(
            List.of(
                new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1)),
                new ReturnHistory("200", LocalDateTime.of(2020, 10, 1, 1, 1))
            )
        );

        Detail actual = expect;

        expect.getReturnHistory().get(0).setId("999");

        assertThat(actual).isEqualTo(expect);
      }

      @Disabled("確実に失敗するので")

      @Test
      public void リストデータをコピーする2() {
        Detail expect = new Detail(
            List.of(
                new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1)),
                new ReturnHistory("200", LocalDateTime.of(2020, 10, 1, 1, 1))
            )
        );

        Detail actual = SerializationUtils.clone(expect);

        expect.getReturnHistory().get(0).setId("999");

        assertThat(actual).isEqualTo(expect);
      }
    }
  }

  @Nested
  public class Jacson {
    ObjectMapper om = new ObjectMapper();

    @Disabled("LocalDateTimeが変換できない")
    @Test
    void test_01() throws IOException {
      Detail expect = new Detail(
          List.of(
              new ReturnHistory("100", LocalDateTime.of(2019, 10, 1, 1, 1)),
              new ReturnHistory("200", LocalDateTime.of(2020, 10, 1, 1, 1))
          )
      );

      Detail actual = om.readValue(om.writeValueAsString(expect), Detail.class);

      SerializationUtils.clone(expect);

      assertThat(actual).isEqualTo(expect);
    }

  }


}
