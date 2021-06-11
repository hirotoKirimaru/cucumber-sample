package kirimaru.biz.domain.spel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelationalAndLogicalOperators {

  @Value("#{1 == 1}") // true
  private boolean equal;

  @Value("#{1 eq 1}") // true
  private boolean equalAlphabetic;

  @Value("#{1 != 1}") // false
  private boolean notEqual;

  @Value("#{1 ne 1}") // false
  private boolean notEqualAlphabetic;

  @Value("#{1 < 1}") // false
  private boolean lessThan;

  @Value("#{1 lt 1}") // false
  private boolean lessThanAlphabetic;

  @Value("#{1 <= 1}") // true
  private boolean lessThanOrEqual;

  @Value("#{1 le 1}") // true
  private boolean lessThanOrEqualAlphabetic;

  @Value("#{1 > 1}") // false
  private boolean greaterThan;

  @Value("#{1 gt 1}") // false
  private boolean greaterThanAlphabetic;

  @Value("#{1 >= 1}") // true
  private boolean greaterThanOrEqual;

  @Value("#{1 ge 1}") // true
  private boolean greaterThanOrEqualAlphabetic;


}
