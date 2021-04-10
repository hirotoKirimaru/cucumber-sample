package kirimaru.biz.domain.nest;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OptionalTests {
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
        base.getDetail().getDetailDetail() == null||
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
