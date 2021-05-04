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
  private String description;

  private String getDescription() {
    return description;
  }

  private int computeMultiple(int num1, int num2) {
    return num1 * num2;
  }

  private int computeMultipleArray(int... nums) {
    int tmp = 1;
    for (int num : nums) {
      tmp *= num;
    }
    return tmp;
  }
}
