package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class NullGroupByTests {

  public record Book(String id, String author) {

  }

  @Test
  void test_01() {
    var param = List.of(
        new Book("1", "きり丸"),
        new Book("2", "きり丸"),
        new Book("3", "ゴリラ"),
        new Book("4", null)
    );
    var actual = param.stream().collect(Collectors.groupingBy(
        e -> Optional.ofNullable(e.author)
    ));

    var expected = new HashMap<>();
    expected.put(Optional.of("きり丸"), List.of(
        new Book("1", "きり丸"),
        new Book("2", "きり丸")
    ));

    expected.put(Optional.of("ゴリラ"), List.of(
        new Book("3", "ゴリラ")
    ));
    expected.put(Optional.empty(), List.of(
        new Book("4", null)
    ));
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void test_02() {
    var param = List.of(
        new Book("1", "きり丸"),
        new Book("2", "きり丸"),
        new Book("3", "ゴリラ"),
        new Book("4", null)
    );
    assertThatThrownBy(
        () -> param.stream().collect(Collectors.groupingBy(
            e -> e.author
        ))
    ).isInstanceOfSatisfying(NullPointerException.class,
        (e) -> assertThat(e.getLocalizedMessage()).isEqualTo("element cannot be mapped to a null key"));
  }
}