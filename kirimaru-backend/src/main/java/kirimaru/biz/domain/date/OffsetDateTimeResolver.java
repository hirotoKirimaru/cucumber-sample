package kirimaru.biz.domain.date;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public interface OffsetDateTimeResolver extends DateTimeResolver {

  default OffsetDateTime now() {
    return OffsetDateTime.now(ZoneId.of("Asia/Tokyo"));
  }


}
