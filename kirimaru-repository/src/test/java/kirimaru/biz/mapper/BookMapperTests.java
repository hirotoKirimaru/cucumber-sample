package kirimaru.biz.mapper;

import kirimaru.biz.domain.book.Book;
import kirimaru.biz.domain.book.Isbn;
import kirimaru.biz.mapper.dto.Book2Dto;
import kirimaru.biz.mapper.dto.BookDto;
import kirimaru.biz.mapper.helper.CommonSetup;
import kirimaru.helper.TestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookMapperTests extends CommonSetup {
  @Autowired
  BookMapper mapper;

  @Test
  void test_01() {

    BookDto book1 = BookDto.builder()
        .isbn("9784798126708")
        .money(1000)
        .author("kirimaru")
        .number(BigInteger.TEN)
        .generateDate(now)
        .generateUser("kirimaru")
        .updateDate(now)
        .updateUser("kirimaru")
        .build();
//    insertBooks(book1);
    mapper.insert(book1);


    List<BookDto> actual = mapper.findAll();
    assertThat(actual).isEqualTo(List.of(book1));
  }


  @Test
  void test_02() {

    BookDto book1 = BookDto.builder()
        .isbn("9784798126708")
        .money(1000)
        .author("kirimaru")
        .number(BigInteger.TEN)
        .generateDate(now)
        .generateUser("kirimaru")
        .updateDate(now)
        .updateUser("kirimaru")
        .build();
//    insertBooks(book1);
    mapper.insert(book1);


    List<BookDto> actual = findBookList();
    assertThat(actual).isEqualTo(List.of(book1));
  }


  @Test
  void test_03() {

    BookDto book1 = BookDto.builder()
        .isbn("9784798126708")
        .money(1000)
        .author("kirimaru")
        .generateDate(now)
        .generateUser("kirimaru")
        .updateDate(now)
        .updateUser("kirimaru")
        .build();
//    insertBooks(book1);
    mapper.insert(book1);

    // WHEN
    List<Book2Dto> actual = findBook2List();

    // THEN
    Book2Dto book2 = Book2Dto.builder()
        .isbn(new Isbn("9784798126708"))
        .money(1000)
        .author("kirimaru")
        .generateDate(now)
        .generateUser("kirimaru")
        .updateDate(now)
        .updateUser("kirimaru")
        .build();

    assertThat(actual).isEqualTo(List.of(book2));
  }

  @Test
  void tekitou() {
    // 依存ができることだけ確認したいので、このテストには意味があまりない
    Book build = TestFactory.createBookBuilder().build();
  }
}
