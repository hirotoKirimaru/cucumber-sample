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
              .map(Parent::getDetail)
              .map(Child::getDetailDetail)
              .map(GrandChild::getAmount)
              .orElse(0)

      ).isEqualTo(0);

    }

    @Test
    void test_02() {
      Parent parent = null;

      if (parent == null ||
          parent.getDetail() == null ||
          parent.getDetail().getDetailDetail() == null ||
          parent.getDetail().getDetailDetail().getTax() == 0) {
        int zero = parent.getDetail().getDetailDetail().getTax();
      }

      assertThat(
          Optional.ofNullable(parent)
              .map(Parent::getDetail)
              .map(Child::getDetailDetail)
              .map(GrandChild::getTax) // int型でも中間はIntegerになる
              .orElse(0)

      ).isEqualTo(0);
    }


    @Test
    void test_03() {
      Parent parent = null;

      assertThat(
          Optional.ofNullable(parent)
              .map(Parent::getDetail)
              .map(Child::getDetailDetail)
              .map(GrandChild::getRate)
              .orElse(BigDecimal.ZERO)

      ).isEqualTo(BigDecimal.ZERO);

    }

    @Test
    void test_04() {
      Parent parent = null;

      if (parent == null ||
          parent.getDetail() == null ||
          parent.getDetail().getDetailDetail() == null ||
          parent.getDetail().getDetailDetail().getRate() == null) {
        BigDecimal zero = parent.getDetail().getDetailDetail().getRate();
      }

      assertThat(
          Optional.ofNullable(parent)
              .map(Parent::getDetail)
              .map(Child::getDetailDetail)
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
          .detailList(baseDetailList)
          .build();

      assertThat(
          Optional.ofNullable(parent.getDetailList())
              .stream().flatMap(Collection::stream)
              .map(Child::getDetailDetailList)
              .filter(Objects::nonNull) // NPE回避用ロジック
              .flatMap(Collection::stream)
              .map(GrandChild::getRate)
              .collect(Collectors.toList())
      ).isEmpty();

    }
  }

}
