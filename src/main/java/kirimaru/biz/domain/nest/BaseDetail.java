package kirimaru.biz.domain.nest;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class BaseDetail {
  private BaseDetailDetail detailDetail;
  private List<BaseDetailDetail> detailDetailList;
}
