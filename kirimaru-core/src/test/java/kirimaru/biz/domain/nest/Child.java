package kirimaru.biz.domain.nest;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Child {
  private GrandChild grandChild;
  private List<GrandChild> grandChildren;
}
