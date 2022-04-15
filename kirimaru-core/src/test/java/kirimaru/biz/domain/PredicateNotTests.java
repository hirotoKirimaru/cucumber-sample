package kirimaru.biz.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PredicateNotTests {

  public record Item(List<Currency> currencyList) {

    public int sumOnlyJpy() {
      return currencyList.stream()
          .filter(Currency::isJpy)
          .mapToInt(Currency::value)
          .sum();
    }

    public int sumNotOnlyJpy() {
      return currencyList.stream()
//          .filter(Currency::isNotJpy)
//          .filter(e -> !e.isJpy())
          .filter(Predicate.not(Currency::isJpy))
          .mapToInt(Currency::value)
          .sum();
    }
  }

  public record Currency(String unit, int value) {

    public boolean isJpy() {
      return "JPY".equals(unit);
    }

    public boolean isNotJpy() {
      return !isJpy();
    }
  }

  @Nested
  class SumOnlyJpy {

    @Test
    void test_01() {
      List<Currency> list = List.of(new Currency("JPY", 100));

      Item item = new Item(list);

      assertThat(item.sumOnlyJpy()).isEqualTo(100);

    }

    @Test
    void test_02() {
      List<Currency> list = List.of(new Currency("JPY", 100), new Currency("JPY", 10),
          new Currency("JPY", 1)

      );

      Item item = new Item(list);

      assertThat(item.sumOnlyJpy()).isEqualTo(111);

    }

    @Test
    void test_03() {
      List<Currency> list = List.of(new Currency("JPY", 100), new Currency("JPY", 10),
          new Currency("JPY", 1), new Currency("USD", 9999));

      Item item = new Item(list);

      assertThat(item.sumOnlyJpy()).isEqualTo(111);

    }
  }

  @Nested
  class SumNotOnlyJpy {

    @Test
    void test_01() {
      List<Currency> list = List.of(
          new Currency("JPY", 100),
          new Currency("JPY", 10),
          new Currency("JPY", 1),
          new Currency("USD", 9999));

      Item item = new Item(list);

      assertThat(item.sumNotOnlyJpy()).isEqualTo(9999);

    }
  }
}