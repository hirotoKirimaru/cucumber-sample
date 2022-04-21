package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MultiGroupingTests {

  public record Book(String id, String language, int year, String salesTerritory, Author author) {

  }

  public record Author(String name) {

  }

//  @Disabled("なにもしていない")
  @Test
  void test_01() {
    var param = List.of(
        new Book("1", "Japanese", 1990, "JP", new Author("きり丸")),
        new Book("2", "Japanese", 1990, "JP", new Author("乱太郎")),
        new Book("3", "Japanese", 1990, "JP", new Author("しんべえ")),
        new Book("4", "Japanese", 1990, "JP", new Author("きり丸"))
    );
    var actual = groupingList(param);

    var expected = List.of(
        new Book("1", "Japanese", 1990, "JP", new Author("きり丸")),
        new Book("4", "Japanese", 1990, "JP", new Author("きり丸")),
        new Book("2", "Japanese", 1990, "JP", new Author("乱太郎")),
        new Book("3", "Japanese", 1990, "JP", new Author("しんべえ"))
    );

    assertThat(actual).isEqualTo(expected);
  }

  private List<Book> groupingList(List<Book> param) {
    var group = param.stream().collect(
        Collectors.groupingBy(Book::language,
            Collectors.groupingBy(Book::year,
                Collectors.groupingBy(Book::author))
        ));

    List list = new ArrayList();
    for (Map<Integer, Map<Author, List<Book>>> value : group.values()) {
      for (Map<Author, List<Book>> stringListMap : value.values()) {
        for (List<Book> books : stringListMap.values()) {
          list.addAll(books);
        }
      }
    }
    return list;
  }
}