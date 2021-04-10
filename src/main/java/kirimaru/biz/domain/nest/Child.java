package kirimaru.biz.domain.nest;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Child {
  private GrandChild detailDetail;
  private List<GrandChild> detailDetailList;
}
