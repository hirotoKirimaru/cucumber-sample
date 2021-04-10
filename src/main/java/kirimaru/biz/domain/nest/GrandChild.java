package kirimaru.biz.domain.nest;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class GrandChild {
  private int tax;
  private Integer amount;
  private BigDecimal rate;
}
