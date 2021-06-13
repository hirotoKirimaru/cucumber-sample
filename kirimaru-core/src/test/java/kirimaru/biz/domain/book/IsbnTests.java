package kirimaru.biz.domain.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsbnTests {

  @Test
  void test_01() {
    new Isbn("9784798126708");
  }
}
