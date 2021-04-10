package kirimaru.biz.domain.nest;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Base {
  private BaseDetail detail;
  private List<BaseDetail> detailList;

}
