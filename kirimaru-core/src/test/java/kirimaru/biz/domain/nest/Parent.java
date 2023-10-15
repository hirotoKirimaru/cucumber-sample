package kirimaru.biz.domain.nest;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Parent {
  private Child child;
  private List<Child> children;

}
