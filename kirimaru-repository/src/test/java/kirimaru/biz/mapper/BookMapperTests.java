package kirimaru.biz.mapper;

import kirimaru.biz.domain.book.Book;
import kirimaru.biz.domain.book.Isbn;
import kirimaru.biz.mapper.helper.CommonSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookMapperTests extends CommonSetup {
//  @Autowired
  BookMapper mapper;

  @Test
  void test_01() {
    Book book1 = Book.builder()
        .id(new Isbn("9784798126708"))
        .money(1000)
        .author("kirimaru")
//        .generateDate()
//        .generateUser()
//        .updateDate()
//        .updateUser()
        .build();
    insertBooks(book1);
  }

}
