package kirimaru.biz.domain.nest;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Parent {
  private Child detail;
  private List<Child> detailList;

}
