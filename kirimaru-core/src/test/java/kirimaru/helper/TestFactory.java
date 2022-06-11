package kirimaru.helper;

import kirimaru.biz.domain.book.Book;

public class TestFactory {

  public static Book.BookBuilder createBookBuilder(){
    return Book.builder();
  }

}
