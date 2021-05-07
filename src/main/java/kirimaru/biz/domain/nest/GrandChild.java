package kirimaru.biz.domain.nest;

import kirimaru.biz.domain.book.CustomBuilderBook;
import kirimaru.biz.domain.book.Isbn;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.Value;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Value
//@Data
@Builder(builderClassName = "Builder")
public class GrandChild {
  private int tax;
  private Integer amount;
  private BigDecimal rate;
  private String description;
  private Map<String, Integer> animals = Map.of("dog", 1, "cat", 3, "mouse", 10);

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

//  public static class Builder {
//    public CustomBuilderBook build() {
//      return new GrandChild(id, money, author);
//    }
//
}
