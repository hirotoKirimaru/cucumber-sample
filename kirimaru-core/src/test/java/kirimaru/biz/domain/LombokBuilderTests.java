package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import kirimaru.biz.domain.book.Book;
import kirimaru.biz.domain.book.CustomBuilderBook;
import kirimaru.biz.domain.book.Isbn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LombokBuilderTests {

  @DisplayName("できるだけ、デフォルトのbuildeを使う")
  @Nested
  class DefaultBuilder {
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

    @DisplayName("Builderで値を設定した後に、デフォルト値で上書きしないこと")
    @Test
    void test_02() {
      String gorilla = "gorilla";
      Book actual = Book.builder()
          .author(gorilla)
          .build();

      Book expect = Book.builder()
          .author(gorilla)
          .build();

      assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("toBuilderクラスの検証")
    @Test
    void test_03() {
      // GIVEN
      Book first = Book.builder()
          .id(new Isbn("9784621303252"))
          .money(100)
          .author("kirimaru")
          .build();

      // WHEN
      Book actual =  first.toBuilder().author("摂津のきり丸").build();

      // ACTUAL
      Book expect = Book.builder()
          .id(new Isbn("9784621303252"))
          .money(100)
          .author("摂津のきり丸")
          .build();


      assertThat(actual).isEqualTo(expect);
    }
  }

  @Nested
  class CustomBuilderBookTests {
    @DisplayName("validate設定に引っかからない場合は同一のものが生成される")
    @Test
    void test_01() {
      CustomBuilderBook actual = CustomBuilderBook.builder()
          .money(100)
          .build();
      CustomBuilderBook expect = CustomBuilderBook.builder()
          .money(100)
          .build();

      assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("validateの設定")
    @Test
    void test_02() {
      assertThatThrownBy(
          () -> CustomBuilderBook.builder().build()
      ).isInstanceOfSatisfying(
          RuntimeException.class,
          (e) -> assertThat(e.getMessage()).isEqualTo("エラー！")
      );
    }

    @DisplayName("ISBNをStringでもISBN型でもBookに渡したときにできるようにする")
    @Test
    void test_03() {
      CustomBuilderBook actual = CustomBuilderBook.builder()
          .id("9784621303252")
          .money(100)
          .author("kirimaru")
          .build();
      CustomBuilderBook expect = CustomBuilderBook.builder()
          .id(new Isbn("9784621303252"))
          .money(100)
          .author("kirimaru")
          .build();

      assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("ISBN側のエラーに引っかかった場合ちゃんとthrowすること")
    @Test
    void test_04() {
      assertThatThrownBy(
          () -> CustomBuilderBook.builder()
              .id("123456789")
              .build()
      ).isInstanceOfSatisfying(
          RuntimeException.class,
          (e) -> assertThat(e.getMessage()).isEqualTo("ISBNの桁数が正しくない")
      );
    }

    @DisplayName("toBuilderで不変条件を満たせなくなった時、ちゃんとエラーが表示されること")
    @Test
    void test_05() {
      CustomBuilderBook actual = CustomBuilderBook.builder()
          .id("9784621303252")
          .money(100)
          .author("kirimaru")
          .build();
      assertThatThrownBy(
          () -> actual.toBuilder()
              .id("123456789")
              .build()
      ).isInstanceOfSatisfying(
          RuntimeException.class,
          (e) -> assertThat(e.getMessage()).isEqualTo("ISBNの桁数が正しくない")
      );
    }

    @DisplayName("旧データは変更されていないこと。変更していないデータは引き継いでいること")
    @Test
    void test_06() {
      CustomBuilderBook oldBook = CustomBuilderBook.builder()
          .money(500)
          .author("kirimaru")
          .build();
      CustomBuilderBook newBook = oldBook.toBuilder()
          .money(1000)
          .build();

      assertThat(oldBook.getMoney()).isEqualTo(500);
      assertThat(newBook.getMoney()).isEqualTo(1000);
      assertThat(newBook.getAuthor()).isEqualTo("kirimaru");
    }
  }

}
