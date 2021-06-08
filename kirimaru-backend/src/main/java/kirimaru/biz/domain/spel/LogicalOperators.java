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
public class LogicalOperators {

  @Value("#{250 > 200 && 200 < 4000}") // true
  private boolean and;

  @Value("#{250 > 200 and 200 < 4000}") // true
  private boolean andAlphabetic;

  @Value("#{400 > 300 || 150 < 100}") // true
  private boolean or;

  @Value("#{400 > 300 or 150 < 100}") // true
  private boolean orAlphabetic;

  @Value("#{!true}") // false
  private boolean not;

  @Value("#{not true}") // false
  private boolean notAlphabetic;

}
