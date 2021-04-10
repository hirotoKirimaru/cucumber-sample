package kirimaru.biz.domain.nest;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BaseDetail {
  private BaseDetailDetail detailDetail;
}
