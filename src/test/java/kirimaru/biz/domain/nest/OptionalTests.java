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
import static org.junit.jupiter.api.Assertions.*;

class OptionalTests {

  @Nested
  class One {
    @Test
    void test_01() {
      Base base = null;

      assertThat(
          Optional.ofNullable(base)
              .map(Base::getDetail)
              .map(BaseDetail::getDetailDetail)
              .map(BaseDetailDetail::getAmount)
              .orElse(0)

      ).isEqualTo(0);

    }

    @Test
    void test_02() {
      Base base = null;

      if (base == null ||
          base.getDetail() == null ||
          base.getDetail().getDetailDetail() == null ||
          base.getDetail().getDetailDetail().getTax() == 0) {
        int zero = base.getDetail().getDetailDetail().getTax();
      }

      assertThat(
          Optional.ofNullable(base)
              .map(Base::getDetail)
              .map(BaseDetail::getDetailDetail)
              .map(BaseDetailDetail::getTax) // int型でも中間はIntegerになる
              .orElse(0)

      ).isEqualTo(0);
    }


    @Test
    void test_03() {
      Base base = null;

      assertThat(
          Optional.ofNullable(base)
              .map(Base::getDetail)
              .map(BaseDetail::getDetailDetail)
              .map(BaseDetailDetail::getRate)
              .orElse(BigDecimal.ZERO)

      ).isEqualTo(BigDecimal.ZERO);

    }

    @Test
    void test_04() {
      Base base = null;

      if (base == null ||
          base.getDetail() == null ||
          base.getDetail().getDetailDetail() == null ||
          base.getDetail().getDetailDetail().getRate() == null) {
        BigDecimal zero = base.getDetail().getDetailDetail().getRate();
      }

      assertThat(
          Optional.ofNullable(base)
              .map(Base::getDetail)
              .map(BaseDetail::getDetailDetail)
              .map(BaseDetailDetail::getRate)
              .orElse(BigDecimal.ZERO)

      ).isEqualTo(BigDecimal.ZERO);

    }
  }

  @DisplayName("ListでNPE回避")
  @Nested
  class List {
    @Test
    void test_01() {
      ArrayList<BaseDetail> baseDetailList = new ArrayList<>();
      baseDetailList.add(BaseDetail.builder().build());

      Base base = Base.builder()
          .detailList(baseDetailList)
          .build();

      assertThat(
          Optional.ofNullable(base.getDetailList())
              .stream().flatMap(Collection::stream)
              .map(BaseDetail::getDetailDetailList)
              .filter(Objects::nonNull) // NPE回避用ロジック
              .flatMap(Collection::stream)
              .map(BaseDetailDetail::getRate)
              .collect(Collectors.toList())
      ).isEmpty();

    }
  }

}
