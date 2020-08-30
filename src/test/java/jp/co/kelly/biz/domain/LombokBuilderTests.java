package jp.co.kelly.biz.domain;

import jp.co.kelly.biz.domain.book.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LombokBuilderTests {

  @DisplayName("Builderのデフォルト値を設定できること")
  @Test
  void test_01() {
    Book actual = Book.builder()
        .build();

    Book expect = Book.builder()
        .author("kirimaru")
        .build();

    assertThat(actual).isEqualTo(expect);
  }

}
