package kirimaru.biz.domain.nest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class OptionalTests {

  @Nested
  class One {
    @Test
    void test_01() {
      Parent parent = null;

      assertThat(
          Optional.ofNullable(parent)
              .map(Parent::getChild)
              .map(Child::getGrandChild)
              .map(GrandChild::getAmount)
              .orElse(0)

      ).isEqualTo(0);

    }

    @Test
    void test_02() {
      Parent parent = null;

      if (parent != null) {
        if (parent.getChild() != null) {
          if (parent.getChild().getGrandChild() != null) {
            int zero = parent.getChild().getGrandChild().getTax();
          }
        }
      }

      assertThat(
          Optional.ofNullable(parent)
              .map(Parent::getChild)
              .map(Child::getGrandChild)
              .map(GrandChild::getTax) // int型でも中間はIntegerになる
              .orElse(0)

      ).isEqualTo(0);
    }


    @Test
    void test_03() {
      Parent parent = null;

      assertThat(
          Optional.ofNullable(parent)
              .map(Parent::getChild)
              .map(Child::getGrandChild)
              .map(GrandChild::getRate)
              .orElse(BigDecimal.ZERO)

      ).isEqualTo(BigDecimal.ZERO);

    }

    @Test
    void test_03_01() {
      Parent parent = Parent.builder()
          .child(Child.builder()
              .grandChild(GrandChild.builder()
                  .rate(BigDecimal.TEN)
                  .build())
              .build())
          .build();

      assertThat(
          Optional.ofNullable(parent)
              .map(Parent::getChild)
              .map(Child::getGrandChild)
              .map(GrandChild::getRate)
              .orElse(BigDecimal.ZERO)

      ).isEqualTo(BigDecimal.TEN);

    }

    @Test
    void test_04() {
      Parent parent = null;

      if (parent != null) {
        if (parent.getChild() != null) {
          if (parent.getChild().getGrandChild() != null) {
            if (parent.getChild().getGrandChild().getRate() != null) {
              BigDecimal zero = parent.getChild().getGrandChild().getRate();
            }
          }
        }
      }
      assertThat(
          Optional.ofNullable(parent)
              .map(Parent::getChild)
              .map(Child::getGrandChild)
              .map(GrandChild::getRate)
              .orElse(BigDecimal.ZERO)

      ).isEqualTo(BigDecimal.ZERO);

    }
  }

  @DisplayName("ListでNPE回避")
  @Nested
  class List {
    @Test
    void test_01() {
      ArrayList<Child> baseDetailList = new ArrayList<>();
      baseDetailList.add(Child.builder().build());

      Parent parent = Parent.builder()
          .children(baseDetailList)
          .build();

      assertThat(
          Optional.ofNullable(parent.getChildren())
              .stream().flatMap(Collection::stream)
              .map(Child::getGrandChildren)
              .filter(Objects::nonNull) // NPE回避用ロジック
              .flatMap(Collection::stream)
              .map(GrandChild::getRate)
              .collect(Collectors.toList())
      ).isEmpty();

    }
  }

}
